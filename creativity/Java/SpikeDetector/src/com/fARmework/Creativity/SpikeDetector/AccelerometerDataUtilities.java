package com.fARmework.Creativity.SpikeDetector;

import java.util.List;

public class AccelerometerDataUtilities 
{
	public float[] getAccelerationProduct(List<AccelerometerData> data)
	{
		float[] product = new float[data.size()];
		
		int i = 0;
		
		for (AccelerometerData unitData : data)
		{
			double squareX = unitData.AccelerationX * unitData.AccelerationX;
			double squareY = unitData.AccelerationY * unitData.AccelerationY;
			double squareZ = unitData.AccelerationZ * unitData.AccelerationZ;
			
			product[i++] = (float) Math.sqrt(squareX + squareY + squareZ);
		}
		
		return product;
	}
}
