package com.fARmework.modules.PositionTracking.WiFi.Java.Data;

import com.fARmework.modules.PositionTracking.Data.*;

public class PositionsPair
{
	public PositionData Position1;
	public PositionData Position2;
	
	public PositionsPair(PositionData position1, PositionData position2)
	{
		Position1 = position1;
		Position2 = position2;
	}
}
