package com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.GesturesData;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.*;
import com.fARmework.modules.SpaceGestures.Java.*;

public class RockSpaceGesture extends SpaceGesture
{
	private static final Direction[] PATTERN = { Direction.Left, Direction.Up, Direction.Right, Direction.Down };
	
	public RockSpaceGesture()
	{
		super(GesturesData.ROCK_NAME, PATTERN);
	}
}
