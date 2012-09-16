package com.fARmework.modules.PositionTracking.Java._impl;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class DirectionCalculator implements IDirectionCalculator 
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
