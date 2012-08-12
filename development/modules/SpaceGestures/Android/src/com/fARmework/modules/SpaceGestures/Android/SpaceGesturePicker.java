package com.fARmework.modules.SpaceGestures.Android;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;

import gueei.binding.Command;
import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SpaceGesturePicker extends View implements IBindableView<SpaceGesturePicker>
{
	private SpaceGestureRecorder _spaceGestureRecorder = new SpaceGestureRecorder();
	private OnSpaceGestureListener _gestureListener;
	
	// onGesture attribute (Android-Binding support)
	private ViewAttribute<SpaceGesturePicker, Command> _onGestureAttribute =
				new ViewAttribute<SpaceGesturePicker, Command>(Command.class, SpaceGesturePicker.this, "onGesture")
				{
				    @Override
				    protected void doSetAttributeValue(final Object newValue)
				    {
				    	setOnGestureListener(new OnSpaceGestureListener()
						{
							@Override
							public void onGesture(View v, SpaceGestureData gesture)
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
	
	public SpaceGesturePicker(Context context)
	{
		super(context);
		initialize();
	}
	
	public SpaceGesturePicker(Context context, AttributeSet attrs)
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
				
				if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
				{
					_spaceGestureRecorder.startRecording(getContext());
				}
				else if (event.getActionMasked() == MotionEvent.ACTION_UP)
				{
					_gestureListener.onGesture(v, _spaceGestureRecorder.stopRecording());
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
	
	public void setOnGestureListener(OnSpaceGestureListener listener)
	{
		_gestureListener = listener;
	}
}
