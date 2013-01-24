package com.fARmework.modules.PositionTracking.WiFi.Java.Data;

import com.fARmework.modules.PositionTracking.Data.*;

public class AccessPointDataWithSignalStrengthAndDistance extends AccessPointDataWithSignalStrength
{
	public double Distance;
	
	public AccessPointDataWithSignalStrengthAndDistance() 
	{ 
	}
	
	public AccessPointDataWithSignalStrengthAndDistance(AccessPointDataWithSignalStrength accessPointData)
	{
		super(accessPointData.MacAddress, accessPointData.Position, accessPointData.Range, accessPointData.SignalStrength);
	}
	
	public AccessPointDataWithSignalStrengthAndDistance(AccessPointDataWithSignalStrength accessPointData, double distance)
	{
		super(accessPointData.MacAddress, accessPointData.Position, accessPointData.Range, accessPointData.SignalStrength);
		
		Distance = distance;
	}
	
	public AccessPointDataWithSignalStrengthAndDistance(String macAddress, PositionData position, int range, int signalStrength, double distance)
	{
		super(macAddress, position, range, signalStrength);
		
		Distance = distance;
	}
}
