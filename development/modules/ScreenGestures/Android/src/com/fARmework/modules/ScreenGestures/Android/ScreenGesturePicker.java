package com.fARmework.modules.ScreenGestures.Android;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;

import gueei.binding.Command;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.MotionEvent;
import android.view.View;

public class ScreenGesturePicker extends View implements IBindableView<ScreenGesturePicker>
{
	private static final int DEFAULT_GESTURE_DELAY = 1000;
	private static final float DEFAULT_LINE_THICKNESS = 3.0f;
	
	private OnScreenGestureListener _gestureListener;
	private ScreenGestureData _gesture = new ScreenGestureData();
	private CountDownTimer _timer;
	
	private int _gestureDelay = DEFAULT_GESTURE_DELAY;
	private float _lineThickness = DEFAULT_LINE_THICKNESS;
	
	private Paint _linePaint;
	private Paint _clearPaint;
	
	private Bitmap _bitmap;
	private Canvas _canvas;
	
	private Float _previousX;
	private Float _previousY;
	
	// onGesture attribute (Android-Binding support)
	private ViewAttribute<ScreenGesturePicker, Command> _onGestureAttribute =
				new ViewAttribute<ScreenGesturePicker, Command>(Command.class, ScreenGesturePicker.this, "onGesture")
				{
				    @Override
				    protected void doSetAttributeValue(final Object newValue)
				    {
				    	setOnGestureListener(new OnScreenGestureListener()
						{
							@Override
							public void onGesture(View v, ScreenGestureData gesture)
							{
								((Command)newValue).Invoke(v, gesture);
							}
						});
				    }
				
				    @Override
				    public Command get()
				    {
				    	return null;
				    }
				};
	
	public ScreenGesturePicker(Context context)
	{
		super(context);
		initialize();
	}
	
	public ScreenGesturePicker(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		String gestureDelay = attrs.getAttributeValue("http://farmework.com/", "gesture_delay");
		
		if (gestureDelay != null)
		{
			_gestureDelay = Integer.valueOf(gestureDelay);
		}
		
		String lineThickness = attrs.getAttributeValue("http://farmework.com/", "line_thickness");
		
		if (lineThickness != null)
		{
			_lineThickness = Float.valueOf(lineThickness);
		}
		
		initialize();
	}

	protected void initialize()
	{
		_linePaint = new Paint();
		_linePaint.setARGB(255, 0, 0, 0);
		_linePaint.setStyle(Paint.Style.STROKE);
		_linePaint.setStrokeWidth(_lineThickness);
		
		_clearPaint = new Paint();
		_linePaint.setARGB(255, 255, 255, 255);
		
		setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (_gestureListener == null)
				{
					return false;
				}
				
				_gesture.addPoint(event.getX(), event.getY());
								
				drawLine(event.getX(), event.getY());
				
				if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
				{
					stopTimer();
				}
				
				if (event.getActionMasked() == MotionEvent.ACTION_UP)
				{
					startTimer(v);
					_previousX = null;
					_previousY = null;
				}
				else if (event.getActionMasked() == MotionEvent.ACTION_CANCEL)
				{
					_gesture = new ScreenGestureData();
				}
				
				return true;
			}
		});
	}

	// Android-Binding support
	@Override
	public ViewAttribute<? extends View, ?> createViewAttribute(String attribute)
	{
		if (attribute.equals("onGesture"))
		{
			return _onGestureAttribute;
		}
		
		return null;
	}
	
	public void setOnGestureListener(OnScreenGestureListener listener)
	{
		_gestureListener = listener;
	}
	
	public int getGestureDelay()
	{
		return _gestureDelay;
	}
	
	public void setGestureDealy(int gestureDelay)
	{
		_gestureDelay = gestureDelay;
	}
	
	public float getLineThickness()
	{
		return _lineThickness;
	}
	
	public void setLineThickness(float lineThickness)
	{
		_lineThickness = lineThickness;
	}
	
	@Override
	protected void onSizeChanged(int newWidth, int newHeight, int previousWidth, int previousHeight)
	{
		int currentWidth = _bitmap != null ? _bitmap.getWidth() : 0;
		int currentHeight =  _bitmap != null ? _bitmap.getHeight() : 0;
		
		if (currentWidth >= newWidth && currentHeight >= newHeight)
		{
			return;
		}
		
		if (currentWidth < newWidth)
		{
			currentWidth = newWidth;
		}
		
		if (currentHeight < newHeight)
		{
			currentHeight = newHeight;
		}
		
		Bitmap bitmap = Bitmap.createBitmap(currentWidth, currentHeight, Bitmap.Config.RGB_565);
		
		Canvas canvas = new Canvas();
		canvas.setBitmap(bitmap);
				
		_bitmap = bitmap;
		_canvas = canvas;
		
		clearCanvas();
	}	
	
	@Override
	public void onDraw(Canvas canvas)
	{
		if (_bitmap != null)
		{
			canvas.drawBitmap(_bitmap, 0, 0, null);
		}
	}
	
	public void clearCanvas()
	{
		if (_bitmap != null)
		{
			_canvas.drawPaint(_clearPaint);
			invalidate();
		}
		
		_previousX = null;
		_previousY = null;
	}
	
	private void drawLine(float x, float y)
	{
		if (_previousX == null || _previousY == null)
		{
			return;
		}
		
		_canvas.drawLine(_previousX, _previousY, x, y, _linePaint);
		
		float minX = Math.min(_previousX, x);
		float maxX = Math.max(_previousX, x);
		float minY = Math.min(_previousY, y);
		float maxY = Math.max(_previousY, y);
		
		invalidate(new Rect((int)(minX - _lineThickness), (int)(minY - _lineThickness), (int)(maxX + _lineThickness), (int)(maxY + _lineThickness)));
		
		_previousX = x;
		_previousY = y;
	}
	
	public void startTimer(final View v)
	{
		if (_timer == null)
		{
			_timer = new CountDownTimer(_gestureDelay, _gestureDelay) 
			{
				@Override
				public void onFinish() 
				{
					_gestureListener.onGesture(v, _gesture);
					_gesture = new ScreenGestureData();
					_timer = null;
					
					clearCanvas();
				}
				
				@Override
				public void onTick(long millisUntilFinished) 
				{
				}
			};
			
			_timer.start();
		}
	}
	
	public void stopTimer()
	{
		if (_timer != null)
		{
			_timer.cancel();
			_timer = null;
		}
	}
}
