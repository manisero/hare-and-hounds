package com.fARmework.HareAndHounds.Server.Logic;

public interface IGameManagerFactory
{
	public IGameManager create(int hareID, int houndsID);
}
