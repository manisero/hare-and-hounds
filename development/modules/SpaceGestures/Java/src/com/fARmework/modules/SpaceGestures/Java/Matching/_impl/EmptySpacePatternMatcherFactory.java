package com.fARmework.modules.SpaceGestures.Java.Matching._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Java.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;

public class EmptySpacePatternMatcherFactory implements ISpacePatternMatcherFactory
{
	private Map<Class<? extends SpaceGesture>, ISpacePatternMatcher> _matchers = new LinkedHashMap<Class<? extends SpaceGesture>, ISpacePatternMatcher>();

	@Override
	public boolean register(Class<? extends SpaceGesture> gesture, ISpacePatternMatcher matcher)
	{
		if (_matchers.containsKey(gesture))
		{
			return false;
		}
		
		_matchers.put(gesture, matcher);
		
		return true;
	}

	@Override
	public boolean unregister(Class<? extends SpaceGesture> gesture)
	{
		if (!_matchers.containsKey(gesture))
		{
			return false;
		}
		
		_matchers.remove(gesture);

		return true;
	}
	
	@Override
	public ISpacePatternMatcher get(Class<? extends SpaceGesture> gestureClass)
	{
		for (Class<?> key = gestureClass; key != null; key = key.getSuperclass())
		{
			if (_matchers.containsKey(key))
			{
				return _matchers.get(gestureClass);
			}
		}
		
		return null;
	}
}
