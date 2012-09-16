package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.google.inject.*;

public class GameManagerFactory implements IGameManagerFactory
{
	private IConnectionManager _connectionManager;
	
	@Inject
	public GameManagerFactory(IConnectionManager connectionManager)
	{
		_connectionManager = connectionManager;
	}
	
	@Override
	public IGameManager create(int hareID, String hareName, int houndsID, String houndsName)
	{
		return new GameManager(_connectionManager, new Game(hareID, hareName, houndsID, houndsName));
	}
}
