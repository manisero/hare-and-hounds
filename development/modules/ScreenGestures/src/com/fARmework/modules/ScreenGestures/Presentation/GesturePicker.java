package com.fARmework.modules.ScreenGestures.Presentation;

import gueei.binding.Command;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class GesturePicker extends Button implements IBindableView<GesturePicker>
{
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
							public void onGesture(View v, String gesture)
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
		
		setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_gestureListener.onGesture(v, "some gesture");
			}
		});
	}
	
	public GesturePicker(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_gestureListener.onGesture(v, "some gesture");
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
