package com.fARmework.modules.PositionTracking.WiFi.Java.Data;

import com.fARmework.modules.PositionTracking.Data.*;

public class AccessPointData
{
	public String MacAddress;
	public PositionData Position;
	public int Range;
	
	public AccessPointData() 
	{ 
	}
	
	public AccessPointData(String macAddress, PositionData position, int range)
	{
		MacAddress = macAddress;
		Position = position;
		Range = range;
	}
}
