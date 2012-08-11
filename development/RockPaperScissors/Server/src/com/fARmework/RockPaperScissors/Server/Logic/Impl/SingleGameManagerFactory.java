package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import com.fARmework.RockPaperScissors.Server.Logic.Game;
import com.fARmework.RockPaperScissors.Server.Logic.IGestureProcessor;
import com.fARmework.RockPaperScissors.Server.Logic.ISingleGameManager;
import com.fARmework.RockPaperScissors.Server.Logic.ISingleGameManagerFactory;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.google.inject.Inject;

public class SingleGameManagerFactory implements ISingleGameManagerFactory
{
	private IConnectionManager _connectionManager;
	private IGestureProcessor _gestureProcessor;
	
	@Inject
	public SingleGameManagerFactory(IConnectionManager connectionManager, IGestureProcessor gestureProcessor)
	{
		_connectionManager = connectionManager;
		_gestureProcessor = gestureProcessor;
	}
	
	@Override
	public ISingleGameManager create(Game game)
	{
		return new SingleGameManager(_connectionManager, _gestureProcessor, game);
	}
}
