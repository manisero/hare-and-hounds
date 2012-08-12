package com.fARmework.RockPaperScissors.Server.Logic._impl;

import com.fARmework.RockPaperScissors.Server.Logic.Game;
import com.fARmework.RockPaperScissors.Server.Logic.ISingleGameManager;
import com.fARmework.RockPaperScissors.Server.Logic.ISingleGameManagerFactory;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.modules.ScreenGestures.Java.IGestureRecognizer;
import com.google.inject.Inject;

public class SingleGameManagerFactory implements ISingleGameManagerFactory
{
	private IConnectionManager _connectionManager;
	private IGestureRecognizer _gestureRecognizer;
	
	@Inject
	public SingleGameManagerFactory(IConnectionManager connectionManager, IGestureRecognizer gestureRecognizer)
	{
		_connectionManager = connectionManager;
		_gestureRecognizer = gestureRecognizer;
	}
	
	@Override
	public ISingleGameManager create(Game game)
	{
		return new SingleGameManager(_connectionManager, _gestureRecognizer, game);
	}
}
