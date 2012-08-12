package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.GesturesData;
import com.fARmework.modules.ScreenGestures.Java.Gestures.PlainGesture;

public class PaperScreenGesture extends PlainGesture
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
