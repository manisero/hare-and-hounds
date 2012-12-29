package com.fARmework.modules.SpaceGestures.Java._impl;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.google.inject.*;
import java.util.*;

public class SpaceGestureRecognizer implements ISpaceGestureRecognizer
{
	private final ISpaceGestureRegistry _gestureRegistry;
	private final ISpaceGestureProcessor _processor;
	private final ISpacePatternMatcherFactory _matcherFactory;
	
	@Inject
	public SpaceGestureRecognizer(ISpaceGestureRegistry gestureRegistry, ISpaceGestureProcessor processor, ISpacePatternMatcherFactory matcherFactory)
	{
		_gestureRegistry = gestureRegistry;
		_processor = processor;
		_matcherFactory = matcherFactory;
	}
	
	@Override
	public String recognize(SpaceGestureData data)
	{
		List<SpaceGesture> gestures = _gestureRegistry.getGestures();
		List<Direction> moves = _processor.process(data);
		
		logDetectedDirections(moves);
		
		double bestMatchingRatio = ISpacePatternMatcher.MIN_MATCHING_RATIO;
		String gestureName = null;
		
		for (SpaceGesture gesture : gestures)
		{
			ISpacePatternMatcher matcher = _matcherFactory.get(gesture.getClass());
			
			double matchingRatio = matcher.match(moves.toArray(new Direction[0]), gesture.getPattern());
					
			if (matchingRatio > bestMatchingRatio)
			{
				bestMatchingRatio = matchingRatio;
				gestureName = gesture.getName();
			}
		}
		
		// TODO: implement matching ratio threshold (return null if bestMatchingRatio is below the threshold)
		return gestureName;
	}
	
	private void logDetectedDirections(List<Direction> directions)
	{
		System.out.println("Detected directions:");
		
		for  (Direction direction : directions)
		{
			System.out.print(direction);
			System.out.print(" ");
		}
		
		System.out.print("\n");
	}
}
