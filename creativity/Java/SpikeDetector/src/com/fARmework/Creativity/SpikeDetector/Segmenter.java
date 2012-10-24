package com.fARmework.Creativity.SpikeDetector;

import java.util.*;

public class Segmenter 
{
	private SpikeState _state = SpikeState.Rising;
	
	private SingleSpike _spike;
	
	private float _lastLength = 0;
	private int _lastIndex = 0;
	
	public List<SegmentRange> getSegments(float[] accelerationValues)
	{
		List<SegmentRange> oscillations = new LinkedList<SegmentRange>();
		
		_spike = new SingleSpike(accelerationValues[0]);
		
		int initialIndex = 0;
		
		for (int index = 0; index < accelerationValues.length; ++index)
		{
			if (index == accelerationValues.length - 1)
			{
				oscillations.add(new SegmentRange(initialIndex, index));
				
				break;
			}
			
			if (!_spike.addNextPoint(accelerationValues[index + 1]))
			{
				if (_lastLength < _spike.getRiseLength())
				{
					if (_state == SpikeState.Falling)
					{
						oscillations.add(new SegmentRange(initialIndex, _lastIndex));
						initialIndex = _lastIndex;
						
						_state = SpikeState.Rising;
					}
				}
				else 
				{
					if(_state == SpikeState.Rising)
					{
						_state = SpikeState.Falling;
					}					
				}
				
				_lastLength = _spike.getRiseLength();
				_lastIndex = index;
				
				_spike = new SingleSpike(accelerationValues[index]);
				_spike.addNextPoint(accelerationValues[index + 1]);
			}
		}
		
		return oscillations;
	}
}
