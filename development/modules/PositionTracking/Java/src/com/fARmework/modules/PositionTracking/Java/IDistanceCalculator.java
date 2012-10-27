package com.fARmework.modules.PositionTracking.Java;

import com.fARmework.modules.PositionTracking.Data.*;

public interface IDistanceCalculator 
{
	double calculateDistance(PositionData first, PositionData second);
}
