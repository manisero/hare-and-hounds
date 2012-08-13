package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.*;

public class ScissorsScreenGesture extends DiffusedScreenGesture
{
	private static final Double[][] PATTERN =
											{
												{	1.0,	0.5,	1.0	},
												{	0.5,	1.0,	0.5	},
												{	1.0,	0.5,	1.0	}
											}; // TODO: define the pattern better
	
	public ScissorsScreenGesture()
	{
		super(GesturesData.SCISSORS_NAME, PATTERN);
	}
}
