package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.*;

public class PaperScreenGesture extends PlainScreenGesture
{
	private static final Boolean[][] PATTERN =
											{
												{ true, true, true, true, true },
												{ true, false, false, false, true },
												{ true, false, false, false, true },
												{ true, false, false, false, true },
												{ true, true, true, true, true }
											};
	
	public PaperScreenGesture()
	{
		super(GesturesData.PAPER_NAME, PATTERN);
	}
}
