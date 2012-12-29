package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.*;

public class RockScreenGesture extends GroupedScreenGesture
{
	private static final Character[][] PATTERN =
											{
												{	'-',	'a',	'b',	'c',	'-'	},
												{	'?',	'a',	'b',	'c',	'?'	},
												{	'd',	'd',	'?',	'e',	'e'	},
												{	'f',	'f',	'-',	'g',	'g'	},
												{	'+',	'+',	'+',	'+',	'+'	},
											}; // TODO: define the pattern better
	
	public RockScreenGesture()
	{
		super(GesturesData.ROCK_NAME, PATTERN);
	}
}
