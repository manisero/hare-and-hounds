package com.fARmework.modules.SpaceGestures.Java.Matching;

import com.fARmework.modules.SpaceGestures.Java.*;

public interface ISpacePatternMatcher
{
	public static double MIN_MATCHING_RATIO = 0.0;
	public static double MAX_MATCHING_RATIO = 1.0;
	
	double match(Direction[] input, Direction[] pattern);
}
