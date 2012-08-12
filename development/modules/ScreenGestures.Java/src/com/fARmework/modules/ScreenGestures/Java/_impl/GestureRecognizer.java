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
	public String recognize(GestureData data) 
	{
		List<Gesture<?>> gestures = _gestureRegistry.getGestures();
		
		for (Gesture<?> gesture : gestures)
		{
			String result = recognizeGesture(data, gesture);
			
			if (result != null)
				return result;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T> String recognizeGesture(GestureData data, Gesture<T> gesture)
	{
		IGestureProcessor<T> processor = _processorFactory.get((Class<? extends Gesture<T>>)gesture.getClass());
		IPatternMatcher<T> matcher = _matcherFactory.get((Class<? extends Gesture<T>>)gesture.getClass());
		
		T[][] grid = processor.getGestureGrid(data, gesture.getPatternSize());
		
		logGestureGrid(gesture.getName(), grid);
		
		return matcher.match(grid, gesture.getPattern()) ? gesture.getName() : null;
	}
	
	private <T> void logGestureGrid(String gestureName, T[][] grid)
	{
		System.out.println(String.format("Gesture grid for %1$s:", gestureName));
		
		for (int x = 0; x != grid.length; x++)
		{
			for (int y = 0; y != grid[x].length; y++)
			{
				System.out.print(grid[x][y] + "\t");
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
}
