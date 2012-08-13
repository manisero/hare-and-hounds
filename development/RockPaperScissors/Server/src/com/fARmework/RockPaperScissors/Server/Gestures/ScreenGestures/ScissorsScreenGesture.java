package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.GesturesData;
import com.fARmework.modules.ScreenGestures.Java.Gestures.DiffusedScreenGesture;

public class ScissorsScreenGesture extends DiffusedScreenGesture
{
	private static final Double[][] PATTERN =
											{
												{	1.0,	0.1,	0.0,	0.1,	1.0	},
												
												{	0.1,	1.0,	0.1,	1.0,	0.1 },
												
												{	0.0,	0.1,	1.0,	0.1,	0.0 },
												
												{	0.1,	1.0,	0.1,	1.0,	0.1 },
												
												{	1.0,	1.0,	1.0,	1.0,	1.0	}
											}; // TODO: define the pattern better
	
	public ScissorsScreenGesture()
	{
		super(GesturesData.SCISSORS_NAME, PATTERN);
	}
}
