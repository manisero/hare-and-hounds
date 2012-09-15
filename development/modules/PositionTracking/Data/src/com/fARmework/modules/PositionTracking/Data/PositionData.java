package com.fARmework.modules.PositionTracking.Data;

public class PositionData
{
	public double Latitude;
	public double Longitude;
	
	public PositionData(double latitude, double longitude)
	{
		Latitude = latitude;
		Longitude = longitude;
	}
	
	@Override
	public String toString()
	{
		return new String(Latitude + ", " + Longitude);
	}
}
