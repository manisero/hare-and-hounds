package com.fARmework.RockPaperScissors.Data;

public class GameResultInfo
{
	public enum GameResult
	{
		Victory,
		Defeat,
		Draw
	}
	
	public GameResult GameResult;
	
	public GameResultInfo(GameResult gameResult)
	{
		GameResult = gameResult;
	}
}
