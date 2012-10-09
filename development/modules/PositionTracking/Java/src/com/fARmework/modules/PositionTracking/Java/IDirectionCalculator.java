package com.fARmework.modules.PositionTracking.Java;

import com.fARmework.modules.PositionTracking.Data.*;

public interface IDirectionCalculator 
{
	double calculateDirection(PositionData current, PositionData target);
}
