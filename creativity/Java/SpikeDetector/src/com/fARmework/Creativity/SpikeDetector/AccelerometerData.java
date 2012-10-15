package com.fARmework.Creativity.SpikeDetector;

public class AccelerometerData 
{
	public float AccelerationX;
	public float AccelerationY;
	public float AccelerationZ;
	
	public AccelerometerData(float[] accelerometerData)
	{
		AccelerationX = accelerometerData[0];
		AccelerationY = accelerometerData[1];
		AccelerationZ = accelerometerData[2];
	}
	
	@Override
	public String toString()
	{
		return "x: " + AccelerationX + " y: " + AccelerationY + " z: " + AccelerationZ;
	}
}
