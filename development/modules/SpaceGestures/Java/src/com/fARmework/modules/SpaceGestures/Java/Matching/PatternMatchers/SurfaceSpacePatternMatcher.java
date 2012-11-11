package com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Java.Direction;

public class SurfaceSpacePatternMatcher extends PlainSpacePatternMatcher
{
	@Override
	public boolean match(Direction[] input, Direction[] pattern)
	{
		if (input.length != pattern.length)
		{
			return false;
		}
		
		int xParts = 0;
		int yParts = 0;
		int zParts = 0;
		
		for (Direction inputDirection: input)
		{
			if (hasXAxisPart(inputDirection))
			{
				++xParts;
			}
			
			if (haxYAxisPart(inputDirection))
			{
				++yParts;
			}
			
			if (hasZAxisPart(inputDirection))
			{
				++zParts;
			}
		}
		
		if (xParts < yParts && xParts < zParts)
		{
			input = trimXAxisPart(input);
		}
		
		else if (yParts < xParts && yParts < zParts)
		{
			input = trimYAxisPart(input);
		}
		
		else if (zParts < xParts && zParts < yParts)
		{
			input = trimZAxisPart(input);
		}
		
		return super.match(input, pattern);
	}
	
	private boolean hasXAxisPart(Direction direction)
	{
		switch(direction)
		{
			case Left:
			case LeftBackward:
			case LeftDown:
			case LeftDownBackward:
			case LeftDownForward:
			case LeftForward:
			case LeftUp:
			case LeftUpBackward:
			case LeftUpForward:
			case Right:
			case RightBackward:
			case RightDown:
			case RightDownBackward:
			case RightDownForward:
			case RightForward:
			case RightUp:
			case RightUpBackward:
			case RightUpForward:
				
				return true;
		
			default:
				
				return false;
		}
	}
	
	private boolean haxYAxisPart(Direction direction)
	{
		switch(direction)
		{
			case LeftDown:
			case LeftDownBackward:
			case LeftDownForward:
			case LeftUp:
			case LeftUpBackward:
			case LeftUpForward:
			case RightDown:
			case RightDownBackward:
			case RightDownForward:
			case RightUp:
			case RightUpBackward:
			case RightUpForward:
			case Down:
			case DownBackward:
			case DownForward:
			case Up:
			case UpBackward:
			case UpForward:
				
				return true;
		
			default:
				
				return false;
		}
	}
	
	private boolean hasZAxisPart(Direction direction)
	{
		switch(direction)
		{
			case LeftBackward:
			case LeftDownBackward:
			case LeftDownForward:
			case LeftForward:
			case LeftUpBackward:
			case LeftUpForward:
			case RightBackward:
			case RightDownBackward:
			case RightDownForward:
			case RightForward:
			case RightUpBackward:
			case RightUpForward:
			case DownBackward:
			case DownForward:
			case UpBackward:
			case UpForward:
			case Backward:
			case Forward:
				
				return true;
		
			default:
				
				return false;
		}
	}
	
	private Direction[] trimXAxisPart(Direction[] input)
	{
		Direction[] output = new Direction[input.length];
		
		for (int i = 0; i < input.length; ++i)
		{
			output[i] = trimXAxisPart(input[i]);
		}
		
		return output;
	}

	private Direction trimXAxisPart(Direction direction)
	{
		switch(direction)
		{
			case LeftBackward:
			case RightBackward:
				
				return Direction.Backward;
				
			case LeftDown:
			case RightDown:
				
				return Direction.Down;
				
			case LeftDownBackward:
			case RightDownBackward:
				
				return Direction.DownBackward;
				
			case LeftDownForward:
			case RightDownForward:
				
				return Direction.DownForward;
				
			case LeftForward:
			case RightForward:
				
				return Direction.Forward;
				
			case LeftUp:
			case RightUp:
				
				return Direction.Up;
				
			case LeftUpBackward:
			case RightUpBackward:
				
				return Direction.UpBackward;
				
			case LeftUpForward:
			case RightUpForward:
				
				return Direction.UpForward;
				
			default:
				
				return direction;
		}		
	}
	
	private Direction[] trimYAxisPart(Direction[] input)
	{
		Direction[] output = new Direction[input.length];
		
		for (int i = 0; i < input.length; ++i)
		{
			output[i] = trimYAxisPart(input[i]);
		}
		
		return output;
	}
	
	private Direction trimYAxisPart(Direction direction)
	{
		switch(direction)
		{
			case LeftDown:
			case LeftUp:
				
				return Direction.Left;
				
			case LeftDownBackward:
			case LeftUpBackward:
				
				return Direction.LeftBackward;
				
			case LeftDownForward:
			case LeftUpForward:
				
				return Direction.LeftForward;
				
			case RightDown:
			case RightUp:
				
				return Direction.Right;
				
			case RightDownBackward:
			case RightUpBackward:
				
				return Direction.RightBackward;
				
			case RightDownForward:
			case RightUpForward:
				
				return Direction.RightForward;

			case DownBackward:
			case UpBackward:
				
				return Direction.Backward;
				
			case DownForward:
			case UpForward:
				
				return Direction.Forward;
		
			default:
				
				return direction;
		}		
	}
	
	private Direction[] trimZAxisPart(Direction[] input)
	{
		Direction[] output = new Direction[input.length];
		
		for (int i = 0; i < input.length; ++i)
		{
			output[i] = trimZAxisPart(input[i]);
		}
		
		return output;
	}
	
	private Direction trimZAxisPart(Direction direction)
	{
		switch(direction)
		{
			case LeftDownBackward:
			case LeftDownForward:
				
				return Direction.LeftDown;
			
			case LeftBackward:
			case LeftForward:
				
				return Direction.Left;
				
			case LeftUpBackward:
			case LeftUpForward:
				
				return Direction.LeftUp;
				
			case RightBackward:
			case RightForward:
				
				return Direction.Right;
				
			case RightDownBackward:	
			case RightDownForward:
			
				return Direction.RightDown;
				
			case RightUpBackward:
			case RightUpForward:
				
				return Direction.RightUp;
				
			case DownBackward:
			case DownForward:
				
				return Direction.Down;
				
			case UpBackward:
			case UpForward:
				
				return Direction.Up;
		
			default:
				
				return direction;
		}
	}
}
