package com.fARmework.HareAndHounds.Server.Logic;

public interface IGameManagerFactory
{
	public IGameManager create(int hareID, String hareName, int houndsID, String houndsName);
}
