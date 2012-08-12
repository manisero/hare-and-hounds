package com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;

public class PlainSpacePatternMatcher implements ISpacePatternMatcher
{
	@Override
	public boolean match(Direction[] input, Direction[] pattern)
	{
		if(input.length != pattern.length)
		{
			return false;
		}
		
		for(int i = 0; i < input.length; ++i)
		{
			if(!input[i].equals(pattern[i]))
			{
				return false;
			}
		}
		
		return true;
	}
}
