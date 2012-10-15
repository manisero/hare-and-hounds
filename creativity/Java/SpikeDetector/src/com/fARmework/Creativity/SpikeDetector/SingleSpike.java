package com.fARmework.Creativity.SpikeDetector;

public class SingleSpike 
{
	private SpikeState _state = SpikeState.Rising; 
	
	private float _lastPoint;
	private float _riseLength = 0.0f;
	
	public SingleSpike(float initialPoint)
	{
		_lastPoint = initialPoint;
		_riseLength = initialPoint;
	}
	
	public boolean addNextPoint(float point)
	{
		if (point >= _lastPoint)
		{
			if (_state == SpikeState.Rising)
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
			if (_state == SpikeState.Rising)
			{
				_state = SpikeState.Falling;
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
