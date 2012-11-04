package com.fARmework.modules.SpaceGestures.Android;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.google.inject.*;

import gueei.binding.*;

public class SpaceGesturePicker extends View implements IBindableView<SpaceGesturePicker>
{
	@Inject
	public ISpaceGestureRecorder _spaceGestureRecorder;
	
	private OnSpaceGestureListener _gestureListener;
	
	private OnClickListener onStartRecordingListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			setBackgroundColor(Color.RED);
			_spaceGestureRecorder.startRecording();
			setOnClickListener(onStopRecordingListener);
		}
	};
	
	private OnClickListener onStopRecordingListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			setBackgroundColor(Color.WHITE);
			_gestureListener.onGesture(v, _spaceGestureRecorder.stopRecording());
			setOnClickListener(onStartRecordingListener);	
		}
	};
	
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
		setOnClickListener(onStartRecordingListener);
		
		/*
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
				
				try { Thread.sleep(200); }
				catch (InterruptedException e) { }
				
				return true;
			}
		});
		*/
	}
	
	public void setSpaceGestureRecorder(ISpaceGestureRecorder spaceGestureRecorder)
	{
		_spaceGestureRecorder = spaceGestureRecorder;
	}
	
	public void setOnGestureListener(OnSpaceGestureListener listener)
	{
		_gestureListener = listener;
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
}
