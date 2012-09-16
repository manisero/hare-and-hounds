package com.fARmework.modules.PositionTracking.Java;

import com.fARmework.modules.PositionTracking.Data.*;

public class DistanceCalculator 
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
