package com.fARmework.modules.PositionTracking.WiFi.Java.Logic._impl;

import com.fARmework.modules.PositionTracking.WiFi.Java.Logic.*;

public class DistanceCalculator implements IDistanceCalculator
{
	@Override
	public double calculateDistance(int signalStrength, int range)
	{
		return (100 - signalStrength) * range;
	}
}
