package com.fARmework.modules.PositionTracking.Java._impl;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class DirectionCalculator implements IDirectionCalculator 
{
	public double calculateDirection(PositionData current, PositionData target)
	{				
		double longitudeDifference = Math.toRadians(target.Longitude - current.Longitude);
		double targetLatitude = Math.toRadians(target.Latitude);
		double currentLatitude = Math.toRadians(current.Latitude);
		
		double y = Math.sin(longitudeDifference) * Math.cos(targetLatitude);
		double x = Math.cos(currentLatitude) * Math.sin(targetLatitude);
		x -= Math.sin(currentLatitude) * Math.cos(targetLatitude) * Math.cos(longitudeDifference);
		
		double direction = Math.atan2(y, x);
		
		if(direction < 0)
		{
			direction += 2 * Math.PI;
		}
		else if(direction > 2 * Math.PI)
		{
			direction -= 2 * Math.PI;
		}
		
		return Math.toDegrees(direction);
	}
}
