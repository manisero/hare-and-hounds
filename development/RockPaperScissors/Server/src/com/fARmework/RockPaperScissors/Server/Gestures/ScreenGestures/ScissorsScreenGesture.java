package com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures;

import com.fARmework.RockPaperScissors.Server.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Gestures.*;

public class ScissorsScreenGesture extends GroupedScreenGesture
{
	private static final Character[][] PATTERN =
											{
												{ 'c', 'c', '-', '-', 'd', 'd'	},
												{ 'c', 'b', 'b', 'b', 'b', 'd'	},
												{ '-', 'b', 'a', 'a', 'b', '-'	},
												{ '-', 'g', 'a', 'a', 'e', '-'	},
												{ 'h', 'g', 'g', 'e', 'e', 'f'	},
												{ 'h', 'h', '-', '-', 'f', 'f'	}
											};
	
	public ScissorsScreenGesture()
	{
		super(GesturesData.SCISSORS_NAME, PATTERN);
	}
}
