package com.fARmework.RockPaperScissors.Server.SpaceGestures;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.*;
import com.fARmework.modules.SpaceGestures.Java.*;

public class RockSpaceGesture extends SpaceGesture
{
	private static final String NAME = "rock";
	private static final Direction[] PATTERN = { Direction.Left, Direction.Up, Direction.Right, Direction.Down };
	
	public RockSpaceGesture()
	{
		super(NAME, PATTERN);
	}
}
