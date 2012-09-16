package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.google.inject.*;
import java.util.*;

public class GameListManager implements IGameListManager
{
	private IConnectionManager _connectionManager;
	private IGameManagerFactory _gameManagerFactory;
	
	private Map<Integer, Game> _games = new LinkedHashMap<Integer, Game>();
	
	@Inject
	public GameListManager(IConnectionManager connectionManager, IGameManagerFactory gameManagerFactory)
	{
		_connectionManager = connectionManager;
		_gameManagerFactory = gameManagerFactory;
	}
	
	@Override
	public void run()
	{
		_connectionManager.startConnection();
		
		_connectionManager.registerDataHandler(NewGameRequest.class, new IDataHandler<NewGameRequest>()
		{
			@Override
			public void handle(int clientID, NewGameRequest data)
			{
				Game newGame = new Game();
				newGame.HostID = clientID;
				newGame.HostName = data.HostName;
				_games.put(clientID, newGame);
			}
		});
	}
}

