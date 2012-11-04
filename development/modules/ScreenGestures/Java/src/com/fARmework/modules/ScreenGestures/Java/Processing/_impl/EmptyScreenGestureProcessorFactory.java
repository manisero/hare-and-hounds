package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.ScreenGestures.Java.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.*;

public class EmptyScreenGestureProcessorFactory implements IScreenGestureProcessorFactory
{
	private Map<Class<? extends ScreenGesture<?>>, IScreenGestureProcessor<?>> _processors = new LinkedHashMap<Class<? extends ScreenGesture<?>>, IScreenGestureProcessor<?>>();
	
	@Override
	public <T> boolean register(Class<? extends ScreenGesture<T>> gestureClass, IScreenGestureProcessor<T> processor)
	{
		if (_processors.containsKey(gestureClass))
		{
			return false;
		}
		
		_processors.put(gestureClass, processor);
		
		return true;
	}

	@Override
	public boolean unregister(Class<? extends ScreenGesture<?>> gestureClass)
	{
		if (!_processors.containsKey(gestureClass))
		{
			return false;
		}
		
		_processors.remove(gestureClass);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> IScreenGestureProcessor<T> get(Class<? extends ScreenGesture<T>> gestureClass)
	{
		for (Class<?> key = gestureClass; key != null; key = key.getSuperclass())
		{
			if (_processors.containsKey(key))
			{
				return (IScreenGestureProcessor<T>)_processors.get(key);
			}
		}
		
		return null;
	}
}
