package com.fARmework.modules.SpaceGestures.Data;

import java.util.LinkedList;

public class SpaceGestureData
{
	public class AccelerometerData
	{
		public float X;
		public float Y;
		public float Z;
		
		public AccelerometerData(float x, float y, float z)
		{
			X = x;
			Y = y;
			Z = z;
		}
		
		@Override
		public String toString()
		{
			return "x: " + X + " y: " + Y + " z: " + Z;
		}
	}
	
	public LinkedList<AccelerometerData> Readings;
	
	public SpaceGestureData()
	{
		Readings = new LinkedList<AccelerometerData>();
	}
	
	public SpaceGestureData(LinkedList<AccelerometerData> readings)
	{
		Readings = readings;
	}
	
	public void addDirection(AccelerometerData data)
	{
		Readings.add(data);
	}
}
