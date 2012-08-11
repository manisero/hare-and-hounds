package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.IGestureProcessor;
import com.fARmework.modules.ScreenGestures.Java.Processing.IGestureProcessorFactory;

import java.util.*;

public class GestureProcessorFactory implements IGestureProcessorFactory 
{
	private Map<Class<? extends IGesture<?>>, IGestureProcessor<?>> _processors;
	
	public GestureProcessorFactory()
	{
		_processors = new LinkedHashMap<Class<? extends IGesture<?>>, IGestureProcessor<?>>();
	}
	
	@Override
	public boolean register(Class<? extends IGesture<?>> gesture,
			IGestureProcessor<?> processor) 
	{
		if(_processors.containsKey(gesture))
		{
			return false;
		}
		
		_processors.put(gesture, processor);
		
		return true;
	}
	
	@Override
	public boolean unregister(Class<? extends IGesture<?>> gesture) 
	{
		if(!_processors.containsKey(gesture))
		{
			return false;
		}
		
		_processors.remove(gesture);
		
		return true;
	}	

	@Override
	@SuppressWarnings("rawtypes")
	public IGestureProcessor<?> get(Class<? extends IGesture> gesture) 
	{
		return _processors.get(gesture);
	}
}
