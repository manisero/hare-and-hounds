package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.HareAndHounds.Server.Logic.IGameManager.IGameEndHandler;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.google.inject.*;
import java.util.*;

public class GameListManager implements IGameListManager
{
	private class GameListItem
	{
		public String HostName;
		public PositionData HostPosition;
		public boolean IsAvailable = true;
		
		public GameListItem(String hostName, PositionData hostPosition)
		{
			HostName = hostName;
			HostPosition = hostPosition;
		}
	}
	
	private IConnectionManager _connectionManager;
	private IGameManagerFactory _gameManagerFactory;
	
	private Map<Integer, GameListItem> _games = new LinkedHashMap<Integer, GameListItem>();
	
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
				if (!_games.containsKey(clientID))
				{
					_games.put(clientID, new GameListItem(data.HostName, data.Position));
				}
				else
				{
					_games.get(clientID).HostPosition = data.Position;
				}
				
				_connectionManager.send(new NewGameResponse(), clientID);
				
				// TODO: move this to JoinGameResponse handler
				_gameManagerFactory
					.create(clientID, data.HostName, 0, "hounds") // TODO: retrieve hounds ID and name
					.startGame(new IGameEndHandler()
					{
						@Override
						public void onGameEnd(Game game)
						{
							_games.remove(game.HareID);
						}
					});
			}
		});
	}
}

