package com.fARmework.RockPaperScissors.Data;

public class GestureInfo
{
	public enum GestureType
	{
		Rock,
		Paper,
		Scissors,
		Unknown
	}
	
	public GestureType GestureType;
	
	public GestureInfo(GestureType gestureType)
	{
		GestureType = gestureType;
	}
}
