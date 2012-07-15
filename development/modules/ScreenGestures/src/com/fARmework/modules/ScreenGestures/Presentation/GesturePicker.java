package com.fARmework.modules.ScreenGestures.Presentation;

import com.fARmework.modules.ScreenGestures.Data.Gesture;

import gueei.binding.Command;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class GesturePicker extends Button implements IBindableView<GesturePicker>
{
	private Gesture _gesture;
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
				if (_gesture == null)
				{
					_gesture = new Gesture();
				}
				
				_gesture.addPoint(event.getX(), event.getY());
				return false;
			}
		});
		
		setOnLongClickListener(new OnLongClickListener()
		{
			
			@Override
			public boolean onLongClick(View v)
			{
				if (_gestureListener != null)
				{
					_gestureListener.onGesture(v, _gesture);
					_gesture = null;
				}
				
				return false;
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
