package com.fARmework.HareAndHounds.Server.Logic;

public interface IGameManager
{
	public interface IGameEndHandler
	{
		public void onGameEnd(int hostID, int guestID);
	}
	
	void startGame(IGameEndHandler gameEndHandler);
}
