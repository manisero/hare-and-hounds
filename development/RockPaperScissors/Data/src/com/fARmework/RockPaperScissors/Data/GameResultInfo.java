package com.fARmework.RockPaperScissors.Data;

import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;

public class GameResultInfo
{
	public enum GameResult
	{
		Victory,
		Defeat,
		Draw
	}
	
	public GestureType PlayerGesture;
	public GestureType OpponentGesture;
	public GameResult GameResult;
	
	public GameResultInfo(GestureType playerGesture, GestureType opponentGesture, GameResult gameResult)
	{
		PlayerGesture = playerGesture;
		OpponentGesture = opponentGesture;
		GameResult = gameResult;
	}
}
