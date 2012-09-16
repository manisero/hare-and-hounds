package com.fARmework.modules.PositionTracking.Java.DirectionCalculating;

import com.fARmework.modules.PositionTracking.Data.*;

public interface IDirectionCalculator 
{
	double calculateDirection(PositionData current, PositionData target);
}
