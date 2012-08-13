package com.fARmework.modules.ScreenGestures.Android;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;

import gueei.binding.Command;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ScreenGesturePicker extends View implements IBindableView<ScreenGesturePicker>
{
	private ScreenGestureData _gesture = new ScreenGestureData();
	private OnScreenGestureListener _gestureListener;
	
	private Bitmap _bitmap;
	private Canvas _canvas;
	private Paint _paint;
	
	// TODO: inject with ISettingsProvider 
	private static final Integer INVALIDATE_RADIUS = 10;
	
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
				
				drawPoint(event.getX(), event.getY());
				
				if (event.getActionMasked() == MotionEvent.ACTION_UP)
				{
					_gestureListener.onGesture(v, _gesture);
					_gesture = new ScreenGestureData();
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
	
	public void drawPoint(float x, float y)
	{
		if(_bitmap != null)
		{
			Rect rectangle = new Rect(
					(int) x - INVALIDATE_RADIUS,
					(int) y - INVALIDATE_RADIUS,
					(int) x + INVALIDATE_RADIUS,
					(int) y + INVALIDATE_RADIUS);
			
			_paint.setARGB(255, 0, 0, 0);
			_canvas.drawPoint(x, y, _paint);
			
			invalidate(rectangle);
		}
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
	}
}
