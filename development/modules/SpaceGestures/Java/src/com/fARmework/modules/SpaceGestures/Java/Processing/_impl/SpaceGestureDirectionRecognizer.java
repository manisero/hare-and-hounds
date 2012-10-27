package com.fARmework.modules.SpaceGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.AccelerometerData;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;

public class SpaceGestureDirectionRecognizer implements ISpaceGestureDirectionRecognizer
{
	@Override
	public List<Direction> getMoveDirections(SpaceGestureData gesture, List<GestureRange> segments)
	{
		List<Direction> moves = new ArrayList<Direction>();
		
		for (GestureRange segment : segments)
		{
			AccelerometerData maxAccelerations = getMaxAccelerations(segment, gesture.Readings);
			
			float x = Math.abs(maxAccelerations.X);
			float y = Math.abs(maxAccelerations.Y);
			float z = Math.abs(maxAccelerations.Z);
			
			if (x >= y && x >= z)
			{
				if (maxAccelerations.X >= 0)
				{
					moves.add(Direction.Left);
				}
				else
				{
					moves.add(Direction.Right);
				}
			}
			else if (y >= x && y >= z)
			{
				if (maxAccelerations.Y >= 0)
				{
					moves.add(Direction.Down);
				}
				else
				{
					moves.add(Direction.Up);
				}
			}
			else if (z >= x && z >= y)
			{
				if (maxAccelerations.Z >= 0)
				{
					moves.add(Direction.Forward);
				}
				else
				{
					moves.add(Direction.Backward);
				}
			}
		}
		
		return moves;
	}

	private SpaceGestureData.AccelerometerData getMaxAccelerations(GestureRange range, List<SpaceGestureData.AccelerometerData> accelerationValues)
	{
		SpaceGestureData.AccelerometerData maxAccelerations = new SpaceGestureData.AccelerometerData(0.0f, 0.0f, 0.0f);
		
		for (int i = range.FirstSample; i <= range.LastSample; ++i)
		{
			if (Math.abs(accelerationValues.get(i).X) > Math.abs(maxAccelerations.X))
			{
				maxAccelerations.X = accelerationValues.get(i).X;
			}
			
			if (Math.abs(accelerationValues.get(i).Y) > Math.abs(maxAccelerations.Y))
			{
				maxAccelerations.Y = accelerationValues.get(i).Y;
			}
			
			if (Math.abs(accelerationValues.get(i).Z) > Math.abs(maxAccelerations.Z))
			{
				maxAccelerations.Z = accelerationValues.get(i).Z;
			}
		}
		
		return maxAccelerations;
	}
}
