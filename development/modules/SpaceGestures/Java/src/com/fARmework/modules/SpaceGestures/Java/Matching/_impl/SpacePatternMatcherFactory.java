package com.fARmework.modules.SpaceGestures.Java.Matching._impl;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import java.util.*;

public class SpacePatternMatcherFactory implements ISpacePatternMatcherFactory
{
	private Map<Class<? extends SpaceGesture>, ISpacePatternMatcher> _matchersMap;
	
	public SpacePatternMatcherFactory()
	{
		_matchersMap = new LinkedHashMap<Class<? extends SpaceGesture>, ISpacePatternMatcher>();
	}
	
	@Override
	public ISpacePatternMatcher get(Class<? extends SpaceGesture> gesture)
	{
		return _matchersMap.get(gesture);
	}

	@Override
	public boolean register(Class<? extends SpaceGesture> gesture, ISpacePatternMatcher matcher)
	{
		if(_matchersMap.containsKey(gesture))
		{
			return false;
		}
		
		_matchersMap.put(gesture, matcher);
		
		return true;
	}

	@Override
	public boolean unregister(Class<? extends SpaceGesture> gesture)
	{
		if(!_matchersMap.containsKey(gesture))
		{
			return false;
		}
		
		_matchersMap.remove(gesture);

		return true;
	}
}
