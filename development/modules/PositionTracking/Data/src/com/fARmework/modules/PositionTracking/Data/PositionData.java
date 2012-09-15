package com.fARmework.modules.PositionTracking.Data;

public class PositionData
{
	public double Longitude;
	public double Latitude;
	
	public PositionData(double longitude, double latitude)
	{
		Longitude = longitude;
		Latitude = latitude;
	}
	
	@Override
	public String toString()
	{
		return new String(Longitude + ", " + Latitude);
	}
}
