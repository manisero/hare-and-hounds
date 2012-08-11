package com.fARmework.modules.ScreenGestures.Java._impl;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Matching.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.*;

import java.util.*;

public class GestureRecognizer implements IGestureRecognizer
{
	private IGestureRegistry _gestureRegistry;
	private IPatternMatcherFactory _matcherFactory;
	private IGestureProcessorFactory _processorFactory;
		
	public GestureRecognizer(IGestureRegistry gestureRegistry,
			IPatternMatcherFactory matcherFactory,
			IGestureProcessorFactory processorFactory)
	{
		_gestureRegistry = gestureRegistry;
		_matcherFactory = matcherFactory;
		_processorFactory = processorFactory;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String recognize(GestureData data) 
	{
		List<IGesture<?>> gestures = _gestureRegistry.getGestures();
		
		for(IGesture<?> gesture : gestures)
		{
			IPatternMatcher matcher = _matcherFactory.get(gesture.getClass());
			IGestureProcessor processor = _processorFactory.get(gesture.getClass());
			
			if(matcher.match(processor.getGestureGrid(data, gesture.getPattern().length), 
					gesture.getPattern()))
			{
				return gesture.getName();
			}
		}
		
		return null;
	}
}
