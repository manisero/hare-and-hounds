package com.fARmework.modules.PositionTracking.Java._impl;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class DirectionCalculator implements IDirectionCalculator 
{
	public double calculateDirection(PositionData current, PositionData target)
	{
		double x = target.Longitude - current.Longitude;
		double y = target.Latitude - current.Latitude;
		
		double direction = 0.5 * Math.PI - Math.atan2(y, x);
		
		if(direction < 0)
		{
			direction += 2 * Math.PI;
		}
		else if(direction > 2 * Math.PI)
		{
			direction -= 2 * Math.PI;
		}
		
		return direction * 180 / Math.PI;
	}
}
