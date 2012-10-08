package com.fARmework.modules.SpaceGestures.Java._impl;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import com.google.inject.*;
import java.util.*;

public class SpaceGestureRecognizer implements ISpaceGestureRecognizer
{
	private final ISpaceGestureRegistry _gestureRegistry;
	private final ISpacePatternMatcherFactory _matcherFactory;
	
	@Inject
	public SpaceGestureRecognizer(ISpaceGestureRegistry gestureRegistry, ISpacePatternMatcherFactory matcherFactory)
	{
		_gestureRegistry = gestureRegistry;
		_matcherFactory = matcherFactory;
	}
	
	@Override
	public String recognize(SpaceGestureData data)
	{
		List<SpaceGesture> gestures = _gestureRegistry.getGestures();
		
		for(SpaceGesture gesture : gestures)
		{
			ISpacePatternMatcher matcher = _matcherFactory.get(gesture.getClass());
			
			if(matcher.match(data.Directions.toArray(new SpaceGestureData.Direction[0]), gesture.getPattern()))
			{
				return gesture.getName();
			}
		}
		
		return null;
	}
}
