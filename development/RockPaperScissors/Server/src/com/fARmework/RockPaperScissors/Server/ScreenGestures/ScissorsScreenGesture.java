package com.fARmework.RockPaperScissors.Server.ScreenGestures;

import com.fARmework.modules.ScreenGestures.Java.Gestures.DiffusedGesture;

public class ScissorsScreenGesture extends DiffusedGesture
{
	private static final String NAME = "scissors";
	private static final Double[][] PATTERN =
											{
												{	1.0,	0.5,	1.0	},
												{	0.5,	1.0,	0.5	},
												{	1.0,	0.5,	1.0	}
											}; // TODO: define the pattern better
	
	public ScissorsScreenGesture()
	{
		super(NAME, PATTERN);
	}
}
