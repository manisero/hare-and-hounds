package com.fARmework.creat.GestureDetector.Matching._impl;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.creat.GestureDetector.Matching.IPatternMatcher;
import com.fARmework.creat.GestureDetector.Matching.IPatternMatcherFactory;

import java.util.*;

public class PatternMatcherFactory implements IPatternMatcherFactory 
{
	private Map<Class<? extends IGesture<?>>, IPatternMatcher<?>> _gestures;
	
	public PatternMatcherFactory()
	{
		_gestures = new LinkedHashMap<Class<? extends IGesture<?>>, IPatternMatcher<?>>();
	}
	
	@Override
	public boolean register(Class<? extends IGesture<?>> gesture,
			IPatternMatcher<?> matcher) 
	{
		if(_gestures.containsKey(gesture))
		{
			return false;
		}
		
		_gestures.put(gesture, matcher);
		
		return true;
	}

	@Override
	public boolean unregister(Class<? extends IGesture<?>> gesture) 
	{
		if(!_gestures.containsKey(gesture))
		{
			return false;
		}
		
		_gestures.remove(gesture);
		
		return true;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public IPatternMatcher<?> get(Class<? extends IGesture> gesture) 
	{
		return _gestures.get(gesture);
	}
}
