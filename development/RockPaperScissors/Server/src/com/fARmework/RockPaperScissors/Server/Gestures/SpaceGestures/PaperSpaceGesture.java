package com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public class PaperSpaceGesture extends PlainSpaceGesture
{
	private static final Direction[] PATTERN = { Direction.Left, Direction.Up, Direction.Right, Direction.Down };
	
	public PaperSpaceGesture()
	{
		super(GesturesData.PAPER_NAME, PATTERN);
	}

}
