package com.fARmework.RockPaperScissors.Server.ScreenGestures;

import com.fARmework.modules.ScreenGestures.Java.Gestures.PlainGesture;

public class PaperScreenGesture extends PlainGesture
{
	private static final String NAME = "paper";
	private static final Boolean[][] PATTERN =  
											{
												{ true, true, true },
												{ true, false, true },
												{ true, true, true }
											}; // TODO: define the pattern better
	
	public PaperScreenGesture()
	{
		super(NAME, PATTERN);
	}
}
