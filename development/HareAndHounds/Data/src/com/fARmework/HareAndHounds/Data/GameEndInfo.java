package com.fARmework.HareAndHounds.Data;

public class GameEndInfo
{
	public enum GameResult
	{
		Victory,
		Defeat
	}
	
	public GameResult Result;
	
	public GameEndInfo(GameResult result)
	{
		Result = result;
	}
}
