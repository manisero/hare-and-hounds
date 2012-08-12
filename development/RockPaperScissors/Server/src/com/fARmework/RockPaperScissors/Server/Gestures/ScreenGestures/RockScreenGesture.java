package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.modules.ScreenGestures.Java.Gestures.PlainScreenGesture;

public class RockScreenGesture extends PlainScreenGesture
{
	private static final String NAME = "rock";
	private static final Boolean[][] PATTERN =
											{
												{ true, true, true, true, true },
												{ true, false, false, false, true },
												{ true, false, false, false, true },
												{ true, false, false, false, true },
												{ true, true, true, true, true }
											};
	
	public RockScreenGesture()
	{
		super(NAME, PATTERN);
	}
}
