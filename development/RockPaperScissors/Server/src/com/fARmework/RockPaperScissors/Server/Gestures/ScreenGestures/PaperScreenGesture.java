package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.GesturesData;
import com.fARmework.modules.ScreenGestures.Java.Gestures.PlainScreenGesture;

public class PaperScreenGesture extends PlainScreenGesture
{
	private static final Boolean[][] PATTERN =  
											{
												{ true, true, true },
												{ true, false, true },
												{ true, true, true }
											}; // TODO: define the pattern better
	
	public PaperScreenGesture()
	{
		super(GesturesData.PAPER_NAME, PATTERN);
	}
}
