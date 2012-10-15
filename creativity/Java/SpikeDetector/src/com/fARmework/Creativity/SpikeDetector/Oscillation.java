package com.fARmework.Creativity.SpikeDetector;

import java.util.LinkedList;
import java.util.List;

public class Oscillation 
{
	private SpikeState _state = SpikeState.Rising;
	private SingleSpike _spike;
	private int _lastIndex = 0;
	private float _lastLength = 0;
	
	public List<OscillationRange> getOscillations(float[] accelerationValues)
	{
		List<OscillationRange> oscillations = new LinkedList<OscillationRange>();
		
		_spike = new SingleSpike(accelerationValues[0]);
		
		int initialIndex = 0;
		
		for (int index = 0; index < accelerationValues.length; ++index)
		{
			System.out.println("Analysing point[" + (index + 1) + "] " + accelerationValues[index]);
			
			if (index == accelerationValues.length - 1)
			{
				System.out.println("Last point!");
				
				if (_state == SpikeState.Falling)
				{
					oscillations.add(new OscillationRange(initialIndex, index));
				}
				
				break;
			}
			
			if (!_spike.addNextPoint(accelerationValues[index + 1]))
			{
				System.out.println("End of spike");
				System.out.println("Last length: " + _lastLength);
				System.out.println("Spike length: " + _spike.getRiseLength());
				
				if (_lastLength < _spike.getRiseLength())
				{
					if (_state == SpikeState.Falling)
					{
						oscillations.add(new OscillationRange(initialIndex, _lastIndex));
						initialIndex = _lastIndex;
						
						_state = SpikeState.Rising;
						
						System.out.println("End of oscillation");
					}
				}
				else 
				{
					if(_state == SpikeState.Rising)
					{
						System.out.println("State change");
						_state = SpikeState.Falling;
					}					
				}
				
				_lastLength = _spike.getRiseLength();
				
				System.out.println("New spike with initial: " + (index + 1) + " (length: " + accelerationValues[index] + ")");
				_spike = new SingleSpike(accelerationValues[index]);
				_spike.addNextPoint(accelerationValues[index + 1]);
				_lastIndex = index;
			}
			else
			{
				System.out.println("Rise length: " + _spike.getRiseLength());
			}
		}
		
		return oscillations;
	}
}
