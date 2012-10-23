package com.fARmework.modules.ScreenGestures.Java.Matching._impl;

import java.util.*;

import com.fARmework.modules.ScreenGestures.Java.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Matching.*;

public class EmptyScreenPatternMatcherFactory implements IScreenPatternMatcherFactory
{
	private Map<Class<? extends ScreenGesture<?>>, IScreenPatternMatcher<?>> _matchers = new LinkedHashMap<Class<? extends ScreenGesture<?>>, IScreenPatternMatcher<?>>();
	
	@Override
	public <T> boolean register(Class<? extends ScreenGesture<T>> gestureClass, IScreenPatternMatcher<T> matcher) 
	{
		if (_matchers.containsKey(gestureClass))
		{
			return false;
		}
		
		_matchers.put(gestureClass, matcher);
		
		return true;
	}

	@Override
	public boolean unregister(Class<? extends ScreenGesture<?>> gestureClass) 
	{
		if (!_matchers.containsKey(gestureClass))
		{
			return false;
		}
		
		_matchers.remove(gestureClass);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> IScreenPatternMatcher<T> get(Class<? extends ScreenGesture<T>> gestureClass) 
	{
		for (Class<?> key = gestureClass; key != null; key = key.getSuperclass())
		{
			if (_matchers.containsKey(key))
			{
				return (IScreenPatternMatcher<T>)_matchers.get(key);
			}
		}
		
		return null;
	}
}
