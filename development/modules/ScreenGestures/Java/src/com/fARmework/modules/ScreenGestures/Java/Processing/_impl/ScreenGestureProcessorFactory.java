package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import com.fARmework.modules.ScreenGestures.Java.Gestures.ScreenGesture;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessor;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessorFactory;

import java.util.*;

public class ScreenGestureProcessorFactory implements IScreenGestureProcessorFactory 
{
	private Map<Class<? extends ScreenGesture<?>>, IScreenGestureProcessor<?>> _processors;
	
	public ScreenGestureProcessorFactory()
	{
		_processors = new LinkedHashMap<Class<? extends ScreenGesture<?>>, IScreenGestureProcessor<?>>();
	}
	
	@Override
	public <T> boolean register(Class<? extends ScreenGesture<T>> gesture, IScreenGestureProcessor<T> processor) 
	{
		if(_processors.containsKey(gesture))
		{
			return false;
		}
		
		_processors.put(gesture, processor);
		
		return true;
	}
	
	@Override
	public boolean unregister(Class<? extends ScreenGesture<?>> gesture) 
	{
		if(!_processors.containsKey(gesture))
		{
			return false;
		}
		
		_processors.remove(gesture);
		
		return true;
	}	

	@SuppressWarnings("unchecked")
	@Override
	public <T> IScreenGestureProcessor<T> get(Class<? extends ScreenGesture<T>> gesture) 
	{
		return (IScreenGestureProcessor<T>)_processors.get(gesture);
	}
}
