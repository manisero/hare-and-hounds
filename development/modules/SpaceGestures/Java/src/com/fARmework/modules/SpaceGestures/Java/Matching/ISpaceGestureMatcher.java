package com.fARmework.modules.SpaceGestures.Java.Matching;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.Direction;

public interface ISpaceGestureMatcher
{
	boolean match(Direction[] input, Direction[] pattern);
}
