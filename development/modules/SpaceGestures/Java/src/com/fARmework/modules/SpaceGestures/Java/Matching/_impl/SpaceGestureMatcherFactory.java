package com.fARmework.modules.SpaceGestures.Java.Matching._impl;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import java.util.*;

public class SpaceGestureMatcherFactory implements ISpaceGestureMatcherFactory
{
	private Map<Class<? extends SpaceGesture>, ISpaceGestureMatcher> _matchersMap;
	
	public SpaceGestureMatcherFactory()
	{
		_matchersMap = new LinkedHashMap<Class<? extends SpaceGesture>, ISpaceGestureMatcher>();
	}
	
	@Override
	public ISpaceGestureMatcher get(Class<? extends SpaceGesture> gesture)
	{
		return _matchersMap.get(gesture);
	}

	@Override
	public boolean register(Class<? extends SpaceGesture> gesture, ISpaceGestureMatcher matcher)
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
