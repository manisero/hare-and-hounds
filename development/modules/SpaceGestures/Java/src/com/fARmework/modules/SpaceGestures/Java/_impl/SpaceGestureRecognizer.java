package com.fARmework.modules.SpaceGestures.Java._impl;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.google.inject.*;
import java.util.*;

public class SpaceGestureRecognizer implements ISpaceGestureRecognizer
{
	private final ISpaceGestureRegistry _gestureRegistry;
	private final ISpacePatternMatcherFactory _matcherFactory;
	private final ISpaceGestureProcessor _processor;
	
	@Inject
	public SpaceGestureRecognizer(ISpaceGestureRegistry gestureRegistry, ISpacePatternMatcherFactory matcherFactory, ISpaceGestureProcessor processor)
	{
		_gestureRegistry = gestureRegistry;
		_matcherFactory = matcherFactory;
		_processor = processor;
	}
	
	@Override
	public String recognize(SpaceGestureData data)
	{
		List<SpaceGesture> gestures = _gestureRegistry.getGestures();
		
		List<Direction> moves = _processor.process(data);
		
		for(SpaceGesture gesture : gestures)
		{
			ISpacePatternMatcher matcher = _matcherFactory.get(gesture.getClass());
			
			if(matcher.match(moves.toArray(new Direction[0]), gesture.getPattern()))
			{
				return gesture.getName();
			}
		}
		
		return null;
	}
}
