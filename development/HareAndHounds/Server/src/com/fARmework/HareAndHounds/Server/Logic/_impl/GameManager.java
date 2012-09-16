package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;

public class GameManager implements IGameManager
{
	private IConnectionManager _connectionManager;
	private Game _game;
	
	public GameManager(IConnectionManager connectionManager, Game game)
	{
		_connectionManager = connectionManager;
		_game = game;
	}
	
	@Override
	public void startGame(IGameEndHandler gameEndHandler)
	{
		_connectionManager.registerDataHandler(PositionData.class, _game.HareID, new IDataHandler<PositionData>()
		{
			@Override
			public void handle(int clientID, PositionData data)
			{
				_game.HarePositions.add(data);
			}
		});
		
		
	}
}
