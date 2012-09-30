package com.fARmework.RockPaperScissors.Server.Logic._impl;

import com.fARmework.RockPaperScissors.Server.Logic.Game;
import com.fARmework.RockPaperScissors.Server.Logic.ISingleGameManager;
import com.fARmework.RockPaperScissors.Server.Logic.ISingleGameManagerFactory;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.modules.ScreenGestures.Java.IScreenGestureRecognizer;
import com.fARmework.modules.SpaceGestures.Java.ISpaceGestureRecognizer;
import com.google.inject.Inject;

public class SingleGameManagerFactory implements ISingleGameManagerFactory
{
	private final IConnectionManager _connectionManager;
	private final IScreenGestureRecognizer _gestureRecognizer;
	private final ISpaceGestureRecognizer _spaceGestureRecognizer;
	
	@Inject
	public SingleGameManagerFactory(IConnectionManager connectionManager, IScreenGestureRecognizer gestureRecognizer, ISpaceGestureRecognizer spaceGestureRecognizer)
	{
		_connectionManager = connectionManager;
		_gestureRecognizer = gestureRecognizer;
		_spaceGestureRecognizer = spaceGestureRecognizer;
	}
	
	@Override
	public ISingleGameManager create(Game game)
	{
		return new SingleGameManager(_connectionManager, _gestureRecognizer, _spaceGestureRecognizer, game);
	}
}
