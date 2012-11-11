package com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public class ScissorsSpaceGesture extends PlainSpaceGesture
{
	private static final Direction[] PATTERN = { Direction.LeftDownBackward, Direction.Right, Direction.LeftUpForward };
	
	public ScissorsSpaceGesture()
	{
		super(GesturesData.SCISSORS_NAME, PATTERN);
	}
}
