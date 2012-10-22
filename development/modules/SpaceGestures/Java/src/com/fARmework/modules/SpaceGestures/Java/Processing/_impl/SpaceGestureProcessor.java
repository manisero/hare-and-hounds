package com.fARmework.modules.SpaceGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.google.inject.*;

public class SpaceGestureProcessor implements ISpaceGestureProcessor
{
	private ISpaceGestureDirectionRecognizer _directionRecognizer;
	private ISpaceGestureFilter _filter;
	private ISpaceGestureSegmentator _segmentator;
	
	@Inject
	public SpaceGestureProcessor(ISpaceGestureDirectionRecognizer directionRecognizer, ISpaceGestureFilter filter, ISpaceGestureSegmentator segmentator)
	{
		_directionRecognizer = directionRecognizer;
		_filter = filter;
		_segmentator = segmentator;
	}
	
	@Override
	public List<Direction> process(SpaceGestureData gesture)
	{
		return _directionRecognizer.getMoveDirections(gesture, _filter.getFilteredSegments(gesture, _segmentator.getGestureSegments(gesture)));
	}
}
