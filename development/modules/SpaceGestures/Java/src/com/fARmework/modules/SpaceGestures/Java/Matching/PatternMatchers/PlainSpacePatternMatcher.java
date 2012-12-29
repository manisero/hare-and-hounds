package com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;

public class PlainSpacePatternMatcher implements ISpacePatternMatcher
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
			if(!input[i].equals(pattern[i]))
			{
				return ISpacePatternMatcher.MIN_MATCHING_RATIO;
			}
		}
		
		return ISpacePatternMatcher.MAX_MATCHING_RATIO;
	}
}
