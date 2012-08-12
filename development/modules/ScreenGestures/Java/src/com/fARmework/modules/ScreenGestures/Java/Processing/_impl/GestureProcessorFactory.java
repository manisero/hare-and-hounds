package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;
import com.fARmework.modules.ScreenGestures.Java.Processing.IGestureProcessor;
import com.fARmework.modules.ScreenGestures.Java.Processing.IGestureProcessorFactory;

import java.util.*;

public class GestureProcessorFactory implements IGestureProcessorFactory 
{
	private Map<Class<? extends Gesture<?>>, IGestureProcessor<?>> _processors;
	
	public GestureProcessorFactory()
	{
		_processors = new LinkedHashMap<Class<? extends Gesture<?>>, IGestureProcessor<?>>();
	}
	
	@Override
	public <T> boolean register(Class<? extends Gesture<T>> gesture, IGestureProcessor<T> processor) 
	{
		if(_processors.containsKey(gesture))
		{
			return false;
		}
		
		_processors.put(gesture, processor);
		
		return true;
	}
	
	@Override
	public boolean unregister(Class<? extends Gesture<?>> gesture) 
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
	public <T> IGestureProcessor<T> get(Class<? extends Gesture<T>> gesture) 
	{
		return (IGestureProcessor<T>)_processors.get(gesture);
	}
}
