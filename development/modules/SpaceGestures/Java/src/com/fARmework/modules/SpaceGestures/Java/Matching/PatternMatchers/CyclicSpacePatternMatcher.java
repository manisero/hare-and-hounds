package com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;

public class CyclicSpacePatternMatcher implements ISpacePatternMatcher
{
	@Override
	public double match(Direction[] input, Direction[] pattern)
	{
		if (input.length != pattern.length)
		{
			return ISpacePatternMatcher.MIN_MATCHING_RATIO;
		}
		
		for (int i = 0; i < input.length; ++i)
		{
			if (input[0].equals(pattern[i]))
			{
				boolean match = true;
				
				for (int j = 0; j < input.length; ++j)
				{
					if (!input[j].equals(pattern[(i + j) % input.length]))
					{
						match = false;
						break;
					}
				}
				
				if (match)
				{
					return ISpacePatternMatcher.MAX_MATCHING_RATIO;
				}
			}
		}
		
		return ISpacePatternMatcher.MIN_MATCHING_RATIO;
	}
}
