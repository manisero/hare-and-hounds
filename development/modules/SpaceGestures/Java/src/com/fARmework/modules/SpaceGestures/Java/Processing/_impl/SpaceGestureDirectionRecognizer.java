package com.fARmework.modules.SpaceGestures.Java.Processing._impl;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Data.*;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.AccelerometerData;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;

public class SpaceGestureDirectionRecognizer implements ISpaceGestureDirectionRecognizer
{
	private static float THRESHOLD = 0.7f;
	
	@Override
	public List<Direction> getMoveDirections(SpaceGestureData gesture, List<GestureRange> segments)
	{
		List<Direction> moves = new LinkedList<Direction>();
		
		for (GestureRange range : segments)
		{
			AccelerometerData maxAccelerations = getMaxAccelerations(range, gesture);
			AccelerometerData meanAccelerations = getMeanAccelerations(range, gesture);
			
			float x = Math.abs(meanAccelerations.X);
			float y = Math.abs(meanAccelerations.Y);
			float z = Math.abs(meanAccelerations.Z);
						
			boolean hasX = false;
			boolean hasY = false;
			boolean hasZ = false;
			
			if (x >= y && x >= z)
			{
				hasX = true;
				
				if (y >= x * THRESHOLD)
				{
					hasY = true;
				}
				
				if(z >= x * THRESHOLD)
				{
					hasZ = true;
				}
			}
			else if (y >= x && y >= z)
			{
				hasY = true;
				
				if (x >= y * THRESHOLD)
				{
					hasX = true;
				}
				
				if (z >= y * THRESHOLD)
				{
					hasZ = true;
				}
			}
			else if (z >= x && z >= y)
			{
				hasZ = true;
				
				if (x >= z * THRESHOLD)
				{
					hasX = true;
				}
				
				if (y >= z * THRESHOLD)
				{
					hasY = true;
				}
			}
			
			moves.add(getSingleDirection(maxAccelerations, hasX, hasY, hasZ));
		}
		
		return moves;
	}
	
	private AccelerometerData getMeanAccelerations(GestureRange range, SpaceGestureData gestureData)
	{
		AccelerometerData[] accelerations = gestureData.Readings.toArray(new AccelerometerData[] {});
		
		AccelerometerData meanAccelerations = new AccelerometerData(0.0f, 0.0f, 0.0f);
		
		int amount = (range.LastSample + 1) - range.FirstSample;
		
		for (int i = range.FirstSample; i <= range.LastSample; ++i)
		{
			meanAccelerations.X += Math.abs(accelerations[i].X);
			meanAccelerations.Y += Math.abs(accelerations[i].Y);
			meanAccelerations.Z += Math.abs(accelerations[i].Z);
		}
		
		meanAccelerations.X /= amount;
		meanAccelerations.Y /= amount;
		meanAccelerations.Z /= amount;
		
		return meanAccelerations;
	}
	
	private AccelerometerData getMaxAccelerations(GestureRange range, SpaceGestureData gestureData)
	{
		AccelerometerData[] accelerations = gestureData.Readings.toArray(new AccelerometerData[] {});
		
		AccelerometerData maxAccelerations = new AccelerometerData(0.0f, 0.0f, 0.0f);
		
		for (int i = range.FirstSample; i <= range.LastSample; ++i)
		{
			if (Math.abs(accelerations[i].X) > Math.abs(maxAccelerations.X))
			{
				maxAccelerations.X = accelerations[i].X;
			}
			
			if (Math.abs(accelerations[i].Y) > Math.abs(maxAccelerations.Y))
			{
				maxAccelerations.Y = accelerations[i].Y;
			}
			
			if (Math.abs(accelerations[i].Z) > Math.abs(maxAccelerations.Z))
			{
				maxAccelerations.Z = accelerations[i].Z;
			}
		}
		
		return maxAccelerations;
	}
	
	private Direction getSingleDirection(AccelerometerData maxAccelerations, boolean hasX, boolean hasY, boolean hasZ)
	{
		if (hasX && hasY && hasZ)
		{
			if (maxAccelerations.X >= 0)
			{
				if (maxAccelerations.Y >= 0)
				{
					if (maxAccelerations.Z >= 0)
					{
						return Direction.LeftDownForward;
					}
					else
					{
						return Direction.LeftDownBackward;
					}
				}
				else
				{
					if (maxAccelerations.Z >= 0)
					{
						return Direction.LeftUpForward;
					}
					else
					{
						return Direction.LeftUpBackward;
					}					
				}
			}
			else
			{
				if (maxAccelerations.Y >= 0)
				{
					if (maxAccelerations.Z >= 0)
					{
						return Direction.RightDownForward;
					}
					else
					{
						return Direction.RightDownBackward;
					}
				}
				else
				{
					if (maxAccelerations.Z >= 0)
					{
						return Direction.RightUpForward;
					}
					else
					{
						return Direction.RightUpBackward;
					}					
				}				
			}
		}
		else if (hasX && hasY)
		{
			if (maxAccelerations.X >= 0)
			{
				if (maxAccelerations.Y >= 0)
				{
					return Direction.LeftDown;
				}
				else
				{
					return Direction.LeftUp;
				}
			}
			else
			{
				if (maxAccelerations.Y >= 0)
				{
					return Direction.RightDown;
				}
				else
				{
					return Direction.RightUp;
				}				
			}
		}
		else if (hasX && hasZ)
		{
			if (maxAccelerations.X >= 0)
			{
				if (maxAccelerations.Z >= 0)
				{
					return Direction.LeftForward;
				}
				else
				{
					return Direction.LeftBackward;
				}
			}
			else
			{
				if (maxAccelerations.Z >= 0)
				{
					return Direction.RightForward;
				}
				else
				{
					return Direction.RightBackward;
				}				
			}			
		}
		else if (hasY && hasZ)
		{
			if (maxAccelerations.Y >= 0)
			{
				if (maxAccelerations.Z >= 0)
				{
					return Direction.DownForward;
				}
				else
				{
					return Direction.DownBackward;
				}
			}
			else
			{
				if (maxAccelerations.Z >= 0)
				{
					return Direction.UpForward;
				}
				else
				{
					return Direction.UpBackward;
				}				
			}			
		}
		else if (hasX)
		{
			if (maxAccelerations.X >= 0)
			{
				return Direction.Left;
			}
			else
			{
				return Direction.Right;
			}
		}
		else if (hasY)
		{
			if (maxAccelerations.Y >= 0)
			{
				return Direction.Down;
			}
			else
			{
				return Direction.Up;
			}			
		}
		else if (hasZ)
		{
			if (maxAccelerations.Z >= 0)
			{
				return Direction.Forward;
			}
			else
			{
				return Direction.Backward;
			}			
		}
		
		return Direction.Unknown;
	}
}
