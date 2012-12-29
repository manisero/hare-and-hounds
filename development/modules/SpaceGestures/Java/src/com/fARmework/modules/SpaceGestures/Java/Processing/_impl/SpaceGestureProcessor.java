package com.fARmework.modules.SpaceGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.google.inject.*;

public class SpaceGestureProcessor implements ISpaceGestureProcessor
{
	private class Builder
	{
		private final SpaceGestureData _gesture;
		private List<GestureRange> _segments;
		
		public Builder(SpaceGestureData gesture)
		{
			_gesture = gesture;
		}
		
		public Builder segment(ISpaceGestureSegmentator segmentator)
		{
			_segments = segmentator.getGestureSegments(_gesture);
			return this;
		}
		
		public Builder filter(ISpaceGestureFilter filter)
		{
			_segments = filter.getFilteredSegments(_gesture, _segments);
			return this;
		}
		
		public List<Direction> recognize(ISpaceGestureDirectionRecognizer recognizer)
		{
			return recognizer.getMoveDirections(_gesture, _segments);
		}
	}
	
	private ISpaceGestureSegmentator _segmentator;
	private ISpaceGestureFilter _filter;
	private ISpaceGestureDirectionRecognizer _recognizer;
	
	@Inject
	public SpaceGestureProcessor(ISpaceGestureSegmentator segmentator, ISpaceGestureFilter filter, ISpaceGestureDirectionRecognizer recognizer)
	{
		_segmentator = segmentator;
		_filter = filter;
		_recognizer = recognizer;
	}
	
	@Override
	public List<Direction> process(SpaceGestureData gesture)
	{
		return new Builder(gesture)
				.segment(_segmentator)
					.filter(_filter)
						.recognize(_recognizer);
	}
}
