package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import com.fARmework.RockPaperScissors.Server.Logic.Game;
import com.fARmework.RockPaperScissors.Server.Logic.IGameFactory;
import com.fARmework.RockPaperScissors.Server.Logic.IGestureProcessor;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.google.inject.Inject;

public class GameFactory implements IGameFactory
{
	private IConnectionManager _connectionManager;
	private IGestureProcessor _gestureProcessor;
	
	@Inject
	public GameFactory(IConnectionManager connectionManager, IGestureProcessor gestureProcessor)
	{
		_connectionManager = connectionManager;
		_gestureProcessor = gestureProcessor;
	}
	
	@Override
	public Game createGame(int hostID, String hostUserName)
	{
		return new Game(_connectionManager, _gestureProcessor, hostID, hostUserName);
	}
}
