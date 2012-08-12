package com.fARmework.modules.SpaceGestures.Java._impl;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import com.google.inject.*;
import java.util.*;

public class SpaceGestureRecognizer implements ISpaceGestureRecognizer
{
	private ISpaceGestureRegistry _gestureRegistry;
	private ISpaceGestureMatcherFactory _matcherFactory;
	
	@Inject
	public SpaceGestureRecognizer(ISpaceGestureRegistry gestureRegistry, ISpaceGestureMatcherFactory matcherFactory)
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
			ISpaceGestureMatcher matcher = _matcherFactory.get(gesture.getClass());
			
			if(matcher.match(data.Directions.toArray(new SpaceGestureData.Direction[0]), gesture.getPattern()))
			{
				return gesture.getName();
			}
		}
		
		return null;
	}
}
