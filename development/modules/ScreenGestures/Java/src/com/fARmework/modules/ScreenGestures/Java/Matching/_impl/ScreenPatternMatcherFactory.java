package com.fARmework.modules.ScreenGestures.Java.Matching._impl;

import com.fARmework.modules.ScreenGestures.Java.Gestures.ScreenGesture;
import com.fARmework.modules.ScreenGestures.Java.Matching.IScreenPatternMatcher;
import com.fARmework.modules.ScreenGestures.Java.Matching.IScreenPatternMatcherFactory;

import java.util.*;

public class ScreenPatternMatcherFactory implements IScreenPatternMatcherFactory 
{
	private Map<Class<? extends ScreenGesture<?>>, IScreenPatternMatcher<?>> _gestures;
	
	public ScreenPatternMatcherFactory()
	{
		_gestures = new LinkedHashMap<Class<? extends ScreenGesture<?>>, IScreenPatternMatcher<?>>();
	}
	
	@Override
	public <T> boolean register(Class<? extends ScreenGesture<T>> gesture, IScreenPatternMatcher<T> matcher) 
	{
		if(_gestures.containsKey(gesture))
		{
			return false;
		}
		
		_gestures.put(gesture, matcher);
		
		return true;
	}

	@Override
	public boolean unregister(Class<? extends ScreenGesture<?>> gesture) 
	{
		if(!_gestures.containsKey(gesture))
		{
			return false;
		}
		
		_gestures.remove(gesture);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> IScreenPatternMatcher<T> get(Class<? extends ScreenGesture<T>> gesture) 
	{
		return (IScreenPatternMatcher<T>)_gestures.get(gesture);
	}
}
