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
	private ScreenGestureData _gesture = new ScreenGestureData();
	private OnScreenGestureListener _gestureListener;
	
	private Bitmap _bitmap;
	private Canvas _canvas;
	private Paint _paint;
	
	private Float _previousX;
	private Float _previousY;
	
	private CountDownTimer _timer;
	
	// TODO: inject with ISettingsProvider 
	private static final Integer DELAY = 2000;
	private static final Float LINE_THICKNESS = 3.0f;
	
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
		initialize();
	}

	protected void initialize()
	{
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
				}
				else if (event.getActionMasked() == MotionEvent.ACTION_CANCEL)
				{
					_gesture = new ScreenGestureData();
				}
				
				return true;
			}
		});
		
		_canvas = new Canvas();
		_paint = new Paint();
		_paint.setStyle(Paint.Style.STROKE);
	}

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
	
	public void drawLine(float x, float y)
	{		
		if(_bitmap != null && _previousX != null && _previousY != null)
		{
			float minX = x < _previousX ? x : _previousX;
			float maxX = x > _previousX ? x : _previousX;
			float minY = y < _previousY ? y : _previousY;
			float maxY = y > _previousY ? y : _previousY;
			
			Rect rectangle = new Rect((int) minX, (int) minY, (int) maxX, (int) maxY);
			
			_paint.setARGB(255, 0, 0, 0);
			_paint.setStrokeWidth(LINE_THICKNESS);
			_canvas.drawLine(_previousX, _previousY, x, y, _paint);
			
			invalidate(rectangle);
		}
		
		_previousX = x;
		_previousY = y;
	}
	
	@Override
	protected void onSizeChanged(int newWidth, int newHeight,
			int previousWidth, int previousHeight)
	{
		int currentWidth = 0;
		int currentHeight = 0;
		
		if(_bitmap != null)
		{
			currentWidth = _bitmap.getWidth();
			currentHeight = _bitmap.getHeight();
		}
		
		if(currentWidth >= newWidth && currentHeight >= newHeight)
		{
			return;
		}
		
		if(currentWidth < newWidth)
		{
			currentWidth = newWidth;
		}
		
		if(currentHeight < newHeight)
		{
			currentHeight = newHeight;
		}
		
		Bitmap bitmap = Bitmap.createBitmap(
				currentWidth,
				currentHeight,
				Bitmap.Config.RGB_565);
		
		Canvas canvas = new Canvas();
		canvas.setBitmap(bitmap);
				
		_bitmap = bitmap;
		_canvas = canvas;
		
		clearCanvas();
	}	
	
	@Override
	public void onDraw(Canvas canvas)
	{
		if(_bitmap != null)
		{
			_paint.setARGB(255, 0, 0, 0);
			canvas.drawBitmap(_bitmap, 0, 0, null);
		}
	}
	
	public void clearCanvas()
	{
		if(_bitmap != null)
		{
			_paint.setARGB(255, 255, 255, 255);
			_canvas.drawPaint(_paint);
			invalidate();
		}
		
		_previousX = null;
		_previousY = null;
	}
	
	public void startTimer(final View v)
	{
		if(_timer == null)
		{
			_timer = new CountDownTimer(DELAY, DELAY) 
			{	
				@Override
				public void onTick(long millisUntilFinished) 
				{ }
				
				@Override
				public void onFinish() 
				{
					_gestureListener.onGesture(v, _gesture);
					_gesture = new ScreenGestureData();
					_timer = null;
					
					clearCanvas();
				}
			};
			
			_timer.start();
		}
	}
	
	public void stopTimer()
	{
		if(_timer != null)
		{
			_timer.cancel();
			_timer = null;
		}
	}
}
