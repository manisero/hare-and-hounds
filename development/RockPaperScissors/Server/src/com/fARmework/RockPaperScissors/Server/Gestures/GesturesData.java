package com.fARmework.RockPaperScissors.Server.Gestures;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;

public class GesturesData
{
	public static final String ROCK_NAME = "rock";
	public static final String PAPER_NAME = "paper";
	public static final String SCISSORS_NAME = "scissors";
	
	private static final Map<String, GestureType> _gestures = new LinkedHashMap<String, GestureType>();
	
	// Initialization
	{
		_gestures.put(ROCK_NAME, GestureType.Rock);
		_gestures.put(PAPER_NAME, GestureType.Paper);
		_gestures.put(SCISSORS_NAME, GestureType.Scissors);
	}
	
	public static GestureType getGestureType(String gestureName)
	{
		return _gestures.containsKey(gestureName) ? _gestures.get(gestureName) : GestureType.Unknown;
	}
}
