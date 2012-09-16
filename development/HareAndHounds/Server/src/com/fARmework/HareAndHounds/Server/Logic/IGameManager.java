package com.fARmework.HareAndHounds.Server.Logic;

public interface IGameManager
{
	public interface IGameEndHandler
	{
		public void onGameEnd(Game game);
	}
	
	void startGame(IGameEndHandler gameEndHandler);
}
