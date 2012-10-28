package com.fARmework.modules.SpaceGestures.Java.Utilities._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Java.Utilities.*;

public class NetAccelerationForceCalculator implements INetAccelerationForceCalculator
{
	@Override
	public float[] getNetAccelerationForce(SpaceGestureData gesture)
	{
		LinkedList<SpaceGestureData.AccelerometerData> readings = gesture.Readings;
		
		float[] netForce = new float[readings.size()];
		
		for (int i = 0; i < readings.size(); ++i)
		{
			double squareX = readings.get(i).X * readings.get(i).X;
			double squareY = readings.get(i).Y * readings.get(i).Y;
			double squareZ = readings.get(i).Z * readings.get(i).Z;
			
			netForce[i] = (float) Math.sqrt(squareX + squareY + squareZ);
		}
		
		return netForce;
	}
}
