package com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public class RockSpaceGesture extends SurfaceSpaceGesture
{
	private static final Direction[] PATTERN = { Direction.Left, Direction.RightUp, Direction.RightDown };
	
	public RockSpaceGesture()
	{
		super(GesturesData.ROCK_NAME, PATTERN);
	}
}
