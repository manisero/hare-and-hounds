package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.*;

public class ScissorsScreenGesture extends GroupedScreenGesture
{
	private static final Character[][] PATTERN =
											{
												{	'+',	'a',	'+'	},
												{	'a',	'+',	'a'	},
												{	'+',	'a',	'+'	}
											}; // TODO: define the pattern better
	
	public ScissorsScreenGesture()
	{
		super(GesturesData.SCISSORS_NAME, PATTERN);
	}
}
