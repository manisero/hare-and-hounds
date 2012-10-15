package com.fARmework.Creativity.SpikeDetector;

import java.util.*;

public class Oscillation 
{
	private SpikeState _state = SpikeState.Rising;
	
	private SingleSpike _spike;
	
	private float _lastLength = 0;
	private int _lastIndex = 0;
	
	public List<OscillationRange> getOscillations(float[] accelerationValues)
	{
		List<OscillationRange> oscillations = new LinkedList<OscillationRange>();
		
		_spike = new SingleSpike(accelerationValues[0]);
		
		int initialIndex = 0;
		
		for (int index = 0; index < accelerationValues.length; ++index)
		{
			if (index == accelerationValues.length - 1)
			{
				if (_state == SpikeState.Falling)
				{
					oscillations.add(new OscillationRange(initialIndex, index));
				}
				
				break;
			}
			
			if (!_spike.addNextPoint(accelerationValues[index + 1]))
			{
				if (_lastLength < _spike.getRiseLength())
				{
					if (_state == SpikeState.Falling)
					{
						oscillations.add(new OscillationRange(initialIndex, _lastIndex));
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
