package com.fARmework.Creativity.SpikeDetector;

import java.util.*;

public class Recognizer
{
	public List<MoveDirection> getMoves(List<SegmentRange> ranges, AccelerometerData[] accelerationValues)
	{
		List<MoveDirection> moves = new LinkedList<MoveDirection>();
		
		for (SegmentRange range : ranges)
		{
			AccelerometerData maxAccelerations = getMaxAccelerations(range, accelerationValues);
			
			float x = Math.abs(maxAccelerations.AccelerationX);
			float y = Math.abs(maxAccelerations.AccelerationY);
			float z = Math.abs(maxAccelerations.AccelerationZ);
			
			if (x >= y && x >= z)
			{
				if (maxAccelerations.AccelerationX >= 0)
				{
					moves.add(MoveDirection.Left);
				}
				else
				{
					moves.add(MoveDirection.Right);
				}
			}
			else if (y >= x && y >= z)
			{
				if (maxAccelerations.AccelerationY >= 0)
				{
					moves.add(MoveDirection.Down);
				}
				else
				{
					moves.add(MoveDirection.Up);
				}
			}
			else if (z >= x && z >= y)
			{
				if (maxAccelerations.AccelerationZ >= 0)
				{
					moves.add(MoveDirection.Forward);
				}
				else
				{
					moves.add(MoveDirection.Backward);
				}
			}
		}
		
		return moves;
	}
	
	public AccelerometerData getMaxAccelerations(SegmentRange range, AccelerometerData[] accelerationValues)
	{
		AccelerometerData maxAccelerations = new AccelerometerData(new float[] { 0.0f, 0.0f, 0.0f });
		
		for (int i = range.OscillationBegin; i <= range.OscillationEnd; ++i)
		{
			if (Math.abs(accelerationValues[i].AccelerationX) > Math.abs(maxAccelerations.AccelerationX))
			{
				maxAccelerations.AccelerationX = accelerationValues[i].AccelerationX;
			}
			
			if (Math.abs(accelerationValues[i].AccelerationY) > Math.abs(maxAccelerations.AccelerationY))
			{
				maxAccelerations.AccelerationY = accelerationValues[i].AccelerationY;
			}
			
			if (Math.abs(accelerationValues[i].AccelerationZ) > Math.abs(maxAccelerations.AccelerationZ))
			{
				maxAccelerations.AccelerationZ = accelerationValues[i].AccelerationZ;
			}
		}
		
		return maxAccelerations;
	}
}
