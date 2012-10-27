package com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public class RockSpaceGesture extends PlainSpaceGesture
{
	private static final Direction[] PATTERN = { Direction.Right, Direction.Up, Direction.Left };
	
	public RockSpaceGesture()
	{
		super(GesturesData.ROCK_NAME, PATTERN);
	}
}
