package com.fARmework.modules.ScreenGestures.Java._impl;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.ScreenGesture;
import com.fARmework.modules.ScreenGestures.Java.Matching.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.*;
import com.google.inject.Inject;

import java.util.*;

public class ScreenGestureRecognizer implements IScreenGestureRecognizer
{
	private final IScreenGestureRegistry _gestureRegistry;
	private final IScreenGestureProcessorFactory _processorFactory;
	private final IScreenPatternMatcherFactory _matcherFactory;
	
	@Inject
	public ScreenGestureRecognizer(IScreenGestureRegistry gestureRegistry, IScreenGestureProcessorFactory processorFactory, IScreenPatternMatcherFactory matcherFactory)
	{
		_gestureRegistry = gestureRegistry;
		_processorFactory = processorFactory;
		_matcherFactory = matcherFactory;
	}

	@Override
	public String recognize(ScreenGestureData data) 
	{
		List<ScreenGesture<?>> gestures = _gestureRegistry.getGestures();
		
		for (ScreenGesture<?> gesture : gestures)
		{
			String result = recognizeGesture(data, gesture);
			
			if (result != null)
				return result;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T> String recognizeGesture(ScreenGestureData data, ScreenGesture<T> gesture)
	{
		IScreenGestureProcessor<T> processor = _processorFactory.get((Class<? extends ScreenGesture<T>>)gesture.getClass());
		IScreenPatternMatcher<T> matcher = _matcherFactory.get((Class<? extends ScreenGesture<T>>)gesture.getClass());
		
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
