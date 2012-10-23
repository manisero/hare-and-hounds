package com.fARmework.modules.SpaceGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.google.inject.*;

public class SpaceGestureProcessor implements ISpaceGestureProcessor
{
	private ISpaceGestureSegmentator _segmentator;
	private ISpaceGestureFilter _filter;
	private ISpaceGestureDirectionRecognizer _directionRecognizer;
	
	@Inject
	public SpaceGestureProcessor(ISpaceGestureSegmentator segmentator, ISpaceGestureFilter filter, ISpaceGestureDirectionRecognizer directionRecognizer)
	{
		_segmentator = segmentator;
		_filter = filter;
		_directionRecognizer = directionRecognizer;
	}
	
	@Override
	public List<Direction> process(SpaceGestureData gesture)
	{
		return _directionRecognizer.getMoveDirections(gesture, _filter.getFilteredSegments(gesture, _segmentator.getGestureSegments(gesture)));
	}
}
