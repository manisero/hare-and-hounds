package com.fARmework.modules.SpaceGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.fARmework.modules.SpaceGestures.Java.Utilities.*;
import com.google.inject.*;

public class SpaceGestureSegmentator implements ISpaceGestureSegmentator
{	
	private static enum State
	{
		Rising,
		Falling
	}
	
	private static class Spike
	{
		private State _state = State.Rising;

		private float _lastPoint;
		private float _riseLength = 0.0f;

		public Spike(float initialPoint)
		{
			_lastPoint = initialPoint;
			_riseLength = initialPoint;
		}

		public boolean addNextPoint(float point)
		{
			if (point >= _lastPoint)
			{
				if (_state == State.Rising)
				{
					_riseLength = point;
				}
				else
				{
					return false;
				}
			}
			else 
			{
				if (_state == State.Rising)
				{
					_state = State.Falling;
				}
			}

			_lastPoint = point;

			return true;
		}

		public float getRiseLength()
		{
			return _riseLength;
		}		
	}
	
	private INetAccelerationForceCalculator _netForceCalculator;
	
	private Spike _spike;
	private State _state = State.Rising;
	
	private float _lastLength = 0;
	private int _lastIndex = 0;
	
	@Inject
	public SpaceGestureSegmentator(INetAccelerationForceCalculator netForceCalculator)
	{
		_netForceCalculator = netForceCalculator;
	}
	
	@Override
	public List<GestureRange> getGestureSegments(SpaceGestureData gesture)
	{
		List<GestureRange> segments = new LinkedList<GestureRange>();
		
		float[] netAcceleration = _netForceCalculator.getNetAccelerationForce(gesture);
		
		_spike = new Spike(netAcceleration[0]);
		
		int initialIndex = 0;
		
		for (int index = 0; index < netAcceleration.length; ++index)
		{
			if (index == netAcceleration.length - 1)
			{
				segments.add(new GestureRange(initialIndex, index));
				
				break;
			}
			
			if (!_spike.addNextPoint(netAcceleration[index + 1]))
			{
				if (_lastLength < _spike.getRiseLength())
				{
					if (_state == State.Falling)
					{
						segments.add(new GestureRange(initialIndex, _lastIndex));
						initialIndex = _lastIndex;
						
						_state = State.Rising;
					}
				}
				else 
				{
					if(_state == State.Rising)
					{
						_state = State.Falling;
					}					
				}
				
				_lastLength = _spike.getRiseLength();
				_lastIndex = index;
				
				_spike = new Spike(netAcceleration[index]);
				_spike.addNextPoint(netAcceleration[index + 1]);
			}
		}
		
		return segments;
	}
}
