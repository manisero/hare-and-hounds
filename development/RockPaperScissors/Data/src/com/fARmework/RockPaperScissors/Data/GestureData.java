package com.fARmework.RockPaperScissors.Data;

public class GestureData
{
	public enum GestureType
	{
		Rock,
		Paper,
		Scissors
	}
	
	private GestureType _gestureType;
	
	public GestureData(GestureType gestureType)
	{
		_gestureType = gestureType;
	}
	
	public GestureType getGestureType()
	{
		return _gestureType;
	}
}
