package com.fARmework.RockPaperScissors.Data;

public class GestureData
{
	public enum GestureType
	{
		Rock,
		Paper,
		Scissors
	}
	
	public GestureType GestureType;
	
	public GestureData(GestureType gestureType)
	{
		GestureType = gestureType;
	}
}
