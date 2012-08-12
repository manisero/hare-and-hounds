package com.fARmework.modules.ScreenGestures.Java.Matching._impl;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;
import com.fARmework.modules.ScreenGestures.Java.Matching.IPatternMatcher;
import com.fARmework.modules.ScreenGestures.Java.Matching.IPatternMatcherFactory;

import java.util.*;

public class PatternMatcherFactory implements IPatternMatcherFactory 
{
	private Map<Class<? extends Gesture<?>>, IPatternMatcher<?>> _gestures;
	
	public PatternMatcherFactory()
	{
		_gestures = new LinkedHashMap<Class<? extends Gesture<?>>, IPatternMatcher<?>>();
	}
	
	@Override
	public <T> boolean register(Class<? extends Gesture<T>> gesture, IPatternMatcher<T> matcher) 
	{
		if(_gestures.containsKey(gesture))
		{
			return false;
		}
		
		_gestures.put(gesture, matcher);
		
		return true;
	}

	@Override
	public boolean unregister(Class<? extends Gesture<?>> gesture) 
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
	public <T> IPatternMatcher<T> get(Class<? extends Gesture<T>> gesture) 
	{
		return (IPatternMatcher<T>)_gestures.get(gesture);
	}
}
