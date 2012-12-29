package com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public class ScissorsSpaceGesture extends SurfaceSpaceGesture
{
	private static final Direction[] PATTERN = { Direction.RightDown, Direction.Left, Direction.RightUp };
	
	public ScissorsSpaceGesture()
	{
		super(GesturesData.SCISSORS_NAME, PATTERN);
	}
}
