package com.fARmework.modules.ScreenGestures.Java._impl;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;
import com.fARmework.modules.ScreenGestures.Java.Matching.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.*;
import com.google.inject.Inject;

import java.util.*;

public class GestureRecognizer implements IGestureRecognizer
{
	private IGestureRegistry _gestureRegistry;
	private IGestureProcessorFactory _processorFactory;
	private IPatternMatcherFactory _matcherFactory;
	
	@Inject
	public GestureRecognizer(IGestureRegistry gestureRegistry,
			IGestureProcessorFactory processorFactory,
			IPatternMatcherFactory matcherFactory)
	{
		_gestureRegistry = gestureRegistry;
		_processorFactory = processorFactory;
		_matcherFactory = matcherFactory;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String recognize(GestureData data) 
	{
		List<Gesture<?>> gestures = _gestureRegistry.getGestures();
		
		for(Gesture<?> gesture : gestures)
		{
			IGestureProcessor processor = _processorFactory.get(gesture.getClass());
			IPatternMatcher matcher = _matcherFactory.get(gesture.getClass());
			
			if(matcher.match(processor.getGestureGrid(data, gesture.getPattern().length), gesture.getPattern()))
			{
				return gesture.getName();
			}
		}
		
		return null;
	}
}
