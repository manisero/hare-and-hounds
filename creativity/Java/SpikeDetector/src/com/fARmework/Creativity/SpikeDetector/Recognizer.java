package com.fARmework.Creativity.SpikeDetector;

import java.util.*;

public class Recognizer
{
	private static float THRESHOLD = 0.7f;
	
	public List<MoveDirection> getMoves(List<SegmentRange> ranges, AccelerometerData[] accelerationValues)
	{
		List<MoveDirection> moves = new LinkedList<MoveDirection>();
		
		for (SegmentRange range : ranges)
		{
			AccelerometerData maxAccelerations = getMaxAccelerations(range, accelerationValues);
			AccelerometerData meanAccelerations = getMeanAccelerations(range, accelerationValues);
			
			float x = Math.abs(meanAccelerations.AccelerationX);
			float y = Math.abs(meanAccelerations.AccelerationY);
			float z = Math.abs(meanAccelerations.AccelerationZ);
						
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
			
			moves.add(getMoveDirection(maxAccelerations, hasX, hasY, hasZ));
		}
		
		return moves;
	}
	
	public AccelerometerData getMeanAccelerations(SegmentRange range, AccelerometerData[] accelerationValues)
	{
		AccelerometerData meanAccelerations = new AccelerometerData(new float[] { 0.0f, 0.0f, 0.0f });
		
		int amount = (range.OscillationEnd + 1) - range.OscillationBegin;
		
		for (int i = range.OscillationBegin; i <= range.OscillationEnd; ++i)
		{
			meanAccelerations.AccelerationX += Math.abs(accelerationValues[i].AccelerationX);
			meanAccelerations.AccelerationY += Math.abs(accelerationValues[i].AccelerationY);
			meanAccelerations.AccelerationZ += Math.abs(accelerationValues[i].AccelerationZ);
		}
		
		meanAccelerations.AccelerationX /= amount;
		meanAccelerations.AccelerationY /= amount;
		meanAccelerations.AccelerationZ /= amount;
		
		return meanAccelerations;
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
	
	private MoveDirection getMoveDirection(AccelerometerData maxAccelerations, boolean hasX, boolean hasY, boolean hasZ)
	{
		if (hasX && hasY && hasZ)
		{
			if (maxAccelerations.AccelerationX >= 0)
			{
				if (maxAccelerations.AccelerationY >= 0)
				{
					if (maxAccelerations.AccelerationZ >= 0)
					{
						return MoveDirection.LeftDownForward;
					}
					else
					{
						return MoveDirection.LeftDownBackward;
					}
				}
				else
				{
					if (maxAccelerations.AccelerationZ >= 0)
					{
						return MoveDirection.LeftUpForward;
					}
					else
					{
						return MoveDirection.LeftUpBackward;
					}					
				}
			}
			else
			{
				if (maxAccelerations.AccelerationY >= 0)
				{
					if (maxAccelerations.AccelerationZ >= 0)
					{
						return MoveDirection.RightDownForward;
					}
					else
					{
						return MoveDirection.RightDownBackward;
					}
				}
				else
				{
					if (maxAccelerations.AccelerationZ >= 0)
					{
						return MoveDirection.RightUpForward;
					}
					else
					{
						return MoveDirection.RightUpBackward;
					}					
				}				
			}
		}
		else if (hasX && hasY)
		{
			if (maxAccelerations.AccelerationX >= 0)
			{
				if (maxAccelerations.AccelerationY >= 0)
				{
					return MoveDirection.LeftDown;
				}
				else
				{
					return MoveDirection.LeftUp;
				}
			}
			else
			{
				if (maxAccelerations.AccelerationY >= 0)
				{
					return MoveDirection.RightDown;
				}
				else
				{
					return MoveDirection.RightUp;
				}				
			}
		}
		else if (hasX && hasZ)
		{
			if (maxAccelerations.AccelerationX >= 0)
			{
				if (maxAccelerations.AccelerationZ >= 0)
				{
					return MoveDirection.LeftForward;
				}
				else
				{
					return MoveDirection.LeftBackward;
				}
			}
			else
			{
				if (maxAccelerations.AccelerationZ >= 0)
				{
					return MoveDirection.RightForward;
				}
				else
				{
					return MoveDirection.RightBackward;
				}				
			}			
		}
		else if (hasY && hasZ)
		{
			if (maxAccelerations.AccelerationY >= 0)
			{
				if (maxAccelerations.AccelerationZ >= 0)
				{
					return MoveDirection.DownForward;
				}
				else
				{
					return MoveDirection.DownBackward;
				}
			}
			else
			{
				if (maxAccelerations.AccelerationZ >= 0)
				{
					return MoveDirection.UpForward;
				}
				else
				{
					return MoveDirection.UpBackward;
				}				
			}			
		}
		else if (hasX)
		{
			if (maxAccelerations.AccelerationX >= 0)
			{
				return MoveDirection.Left;
			}
			else
			{
				return MoveDirection.Right;
			}
		}
		else if (hasY)
		{
			if (maxAccelerations.AccelerationY >= 0)
			{
				return MoveDirection.Down;
			}
			else
			{
				return MoveDirection.Up;
			}			
		}
		else if (hasZ)
		{
			if (maxAccelerations.AccelerationZ >= 0)
			{
				return MoveDirection.Forward;
			}
			else
			{
				return MoveDirection.Backward;
			}			
		}
		
		return MoveDirection.Unknown;
	}
}
