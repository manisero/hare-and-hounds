package com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Java.Direction;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;

public class SurfaceSpacePatternMatcher extends PlainSpacePatternMatcher
{
	@Override
	public double match(Direction[] input, Direction[] pattern)
	{
		if (input.length != pattern.length)
		{
			return ISpacePatternMatcher.MIN_MATCHING_RATIO;
		}
		
		int xParts = 0;
		int yParts = 0;
		int zParts = 0;
		
		for (Direction inputDirection: input)
		{
			if (inputDirection.hasPart(Direction.Left) ||
				inputDirection.hasPart(Direction.Right))
			{
				++xParts;
			}
			
			if (inputDirection.hasPart(Direction.Up) ||
				inputDirection.hasPart(Direction.Down))
			{
				++yParts;
			}
			
			if (inputDirection.hasPart(Direction.Backward) ||
				inputDirection.hasPart(Direction.Forward))
			{
				++zParts;
			}
		}
		
		if (xParts < yParts && xParts < zParts)
		{
			input = Direction.trimParts(input, Direction.Left, Direction.Right);
		}
		else if (yParts < xParts && yParts < zParts)
		{
			input = Direction.trimParts(input, Direction.Up, Direction.Down);
		}
		else if (zParts < xParts && zParts < yParts)
		{
			input = Direction.trimParts(input, Direction.Backward, Direction.Forward);
		}
		
		return super.match(input, pattern);
	}
}
