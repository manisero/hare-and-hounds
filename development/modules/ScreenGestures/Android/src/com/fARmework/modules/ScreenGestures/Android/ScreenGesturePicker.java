package com.fARmework.modules.ScreenGestures.Android;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;

import gueei.binding.Command;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ScreenGesturePicker extends View implements IBindableView<ScreenGesturePicker>
{
	private ScreenGestureData _gesture = new ScreenGestureData();
	private OnScreenGestureListener _gestureListener;
	
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
}
