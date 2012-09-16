package com.fARmework.HareAndHounds.Server.Logic;

public interface IGameManager
{
	public interface IGameStartHandler
	{
		public void onGameStart(Game game);
	}
	
	public interface IGameEndHandler
	{
		public void onGameEnd(Game game);
	}
	
	public void startGame(IGameStartHandler gameStartHandler, IGameEndHandler gameEndHandler);
}
