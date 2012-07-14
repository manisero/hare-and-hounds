package com.fARmework.modules.ScreenGestures.Presentation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class GesturePicker extends Button
{
	private OnGestureListener _gestureListener;
	
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
	
	public void setOnGestureListener(OnGestureListener listener)
	{
		_gestureListener = listener;
	}
}
