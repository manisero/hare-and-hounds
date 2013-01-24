package com.fARmework.modules.PositionTracking.WiFi.Java.Data;

import com.fARmework.modules.PositionTracking.Data.*;

public class AccessPointDataWithSignalStrength extends AccessPointData
{
	public int SignalStrength;
	
	public AccessPointDataWithSignalStrength() 
	{ 
	}
	
	public AccessPointDataWithSignalStrength(AccessPointData accessPointData)
	{
		super(accessPointData.MacAddress, accessPointData.Position, accessPointData.Range);
	}
	
	public AccessPointDataWithSignalStrength(AccessPointData accessPointData, int signalStrength)
	{
		super(accessPointData.MacAddress, accessPointData.Position, accessPointData.Range);
		
		SignalStrength = signalStrength;
	}
	
	public AccessPointDataWithSignalStrength(String macAddress, PositionData position, int range, int signalStrength)
	{
		super(macAddress, position, range);
		
		SignalStrength = signalStrength;
	}
}
