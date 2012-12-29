package com.fARmework.modules.PositionTracking.Java._impl;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class DistanceCalculator implements IDistanceCalculator
{	
	public double calculateDistance(PositionData first, PositionData second)
	{
		final double SCALE_FACTOR = 12756274;
		
		double distance = Math.cos(Math.PI * first.Longitude / 180);
		distance *= Math.pow(first.Latitude - second.Latitude, 2.0);
		distance += Math.pow(first.Longitude - second.Longitude, 2.0);
		distance = Math.sqrt(distance);
		distance *= Math.PI * SCALE_FACTOR / 360.0;
		
		return distance;
	}
}
