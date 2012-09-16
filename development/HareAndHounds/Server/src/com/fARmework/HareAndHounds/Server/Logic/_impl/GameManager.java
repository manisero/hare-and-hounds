package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;

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
		_connectionManager.registerDataHandler(HarePositionData.class, _game.HareID, new IDataHandler<HarePositionData>()
		{
			@Override
			public void handle(int clientID, HarePositionData data)
			{
				_game.HarePositions.add(data.Position);
			}
		});
	}
}
