package com.fARmework.modules.PositionTracking.Java;

import com.fARmework.modules.PositionTracking.Data.*;

public class DirectionCalculator 
{
	public double calculateDirection(PositionData current, PositionData target)
	{
		double x = target.Latitude - current.Latitude;
		double y = target.Longitude - current.Longitude;
		
		double direction = Math.atan2(y, x);
		
		if(direction < 0)
		{
			direction += 2 * Math.PI;
		}
		
		return direction * 180 / Math.PI;
	}
}
