package com.fARmework.modules.ScreenGestures.Presentation;

import com.fARmework.modules.ScreenGestures.Data.Gesture;

import gueei.binding.Command;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GesturePicker extends View implements IBindableView<GesturePicker>
{
	private Gesture _gesture = new Gesture();
	private OnGestureListener _gestureListener;
	
	// onGesture attribute (Android-Binding support)
	private ViewAttribute<GesturePicker, Command> _onGestureAttribute =
				new ViewAttribute<GesturePicker, Command>(Command.class, GesturePicker.this, "onGesture")
				{
				    @Override
				    protected void doSetAttributeValue(final Object newValue)
				    {
				    	setOnGestureListener(new OnGestureListener()
						{
							@Override
							public void onGesture(View v, Gesture gesture)
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
	
	public GesturePicker(Context context)
	{
		super(context);
		initialize();
	}
	
	public GesturePicker(Context context, AttributeSet attrs)
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
					_gesture = new Gesture();
				}
				else if (event.getActionMasked() == MotionEvent.ACTION_CANCEL)
				{
					_gesture = new Gesture();
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
	
	public void setOnGestureListener(OnGestureListener listener)
	{
		_gestureListener = listener;
	}
}
