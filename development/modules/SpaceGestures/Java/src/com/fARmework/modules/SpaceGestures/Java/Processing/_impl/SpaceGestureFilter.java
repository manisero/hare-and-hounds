package com.fARmework.modules.SpaceGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.fARmework.modules.SpaceGestures.Java.Utilities.*;
import com.google.inject.*;

public class SpaceGestureFilter implements ISpaceGestureFilter
{
	private static final Float THRESHOLD = 0.5f;		//TODO: provide this
	
	private INetAccelerationForceCalculator _netForceCalculator;
	
	@Inject
	public SpaceGestureFilter(INetAccelerationForceCalculator netForceCalculator)
	{
		_netForceCalculator = netForceCalculator;
	}
	
	@Override
	public List<GestureRange> getFilteredSegments(SpaceGestureData gesture, List<GestureRange> segments)
	{
		List<GestureRange> filteredSegments = new ArrayList<GestureRange>();
		
		float[] netAcceleration = _netForceCalculator.getNetAccelerationForce(gesture);
		
		float globalMaximum = getMaximum(new GestureRange(0, netAcceleration.length - 1), netAcceleration);
		
		for (GestureRange range : segments)
		{
			float ratio = getMaximum(range, netAcceleration) / globalMaximum;
			
			if (ratio > THRESHOLD)
			{
				filteredSegments.add(range);
			}
		}
		
		return filteredSegments;
	}
	
	private float getMaximum(GestureRange range, float[] values)
	{
		float maximum = 0.0f;
		
		for (int i = range.FirstSample; i <= range.LastSample; ++i)
		{
			if (values[i] > maximum)
			{
				maximum = values[i];
			}
		}
		
		return maximum;
	}
}
