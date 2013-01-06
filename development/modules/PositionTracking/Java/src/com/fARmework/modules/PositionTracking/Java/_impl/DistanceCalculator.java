package com.fARmework.modules.PositionTracking.Java._impl;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class DistanceCalculator implements IDistanceCalculator
{	
	public double calculateDistance(PositionData first, PositionData second)
	{
		final double SCALE_FACTOR = 12756274;
		
		double longitudeDifference = second.Longitude - first.Longitude;
		
		double distance = Math.cos(Math.PI * first.Longitude / 180);
		distance *= second.Latitude - first.Latitude;
		distance *= distance;
		distance += longitudeDifference * longitudeDifference;
		distance = Math.sqrt(distance);
		distance *= Math.PI * SCALE_FACTOR / 360.0;
		
		return distance;
	}
}
