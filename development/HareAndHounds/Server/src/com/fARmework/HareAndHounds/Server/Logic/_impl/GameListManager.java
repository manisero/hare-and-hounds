package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Data.JoinGameResponse.JoinGameResponseType;
import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.HareAndHounds.Server.Logic.IGameManager.IGameEndHandler;
import com.fARmework.core.server.Connection.*;
import com.fARmework.core.server.Data.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;
import com.google.inject.*;
import java.util.*;

public class GameListManager implements IGameListManager
{
	private class GameListItem
	{
		public int HostID;
		public String HostName;
		public PositionData HostPosition;
		public boolean IsAvailable = true;
		
		public GameListItem(int hostID, String hostName, PositionData hostPosition)
		{
			HostID = hostID;
			HostName = hostName;
			HostPosition = hostPosition;
		}
	}
	
	private IConnectionManager _connectionManager;
	private IDistanceCalculator _distanceCalculator;
	private IGameManagerFactory _gameManagerFactory;
	private ISettingsProvider _settingsProvider;
	
	private Map<Integer, GameListItem> _games = new LinkedHashMap<Integer, GameListItem>();
	
	@Inject
	public GameListManager(IConnectionManager connectionManager, IDistanceCalculator distanceCalculator, IGameManagerFactory gameManagerFactory, ISettingsProvider settingsProvider)
	{
		_connectionManager = connectionManager;
		_distanceCalculator = distanceCalculator;
		_gameManagerFactory = gameManagerFactory;
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void run()
	{
		_connectionManager.registerDataHandler(NewGameRequest.class, new IDataHandler<NewGameRequest>()
		{
			@Override
			public void handle(int clientID, NewGameRequest data)
			{
				if (!_games.containsKey(clientID))
				{
					_games.put(clientID, new GameListItem(clientID, data.HostName, data.Position));
				}
				else
				{
					_games.get(clientID).HostPosition = data.Position;
				}
				
				_connectionManager.send(new NewGameResponse(), clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameListRequest.class, new IDataHandler<GameListRequest>()
		{
			@Override
			public void handle(int clientID, GameListRequest data)
			{
				GameListResponse response = new GameListResponse();
				
				for (GameListItem item : _games.values())
				{
					if (item.IsAvailable && _distanceCalculator.calculateDistance(item.HostPosition, data.Position) <= _settingsProvider.getGameRange())
					{
						response.Games.put(item.HostID, item.HostName);
					}
				}
				
				_connectionManager.send(response, clientID);
			}
		});
		
		_connectionManager.registerDataHandler(JoinGameRequest.class, new IDataHandler<JoinGameRequest>()
		{
			@Override
			public void handle(int clientID, JoinGameRequest data)
			{
				if (!_games.containsKey(data.HostID) || !_games.get(data.HostID).IsAvailable)
				{
					_connectionManager.send(new JoinGameResponse(JoinGameResponseType.Unavailable), clientID);
				}
				else
				{
					data.GuestID = clientID;
					_connectionManager.send(data, data.HostID);
				}
			}
		});
		
		_connectionManager.registerDataHandler(JoinGameResponse.class, new IDataHandler<JoinGameResponse>()
		{
			@Override
			public void handle(int clientID, JoinGameResponse data)
			{
				if (data.Response == JoinGameResponseType.Accept)
				{
					GameListItem item = _games.get(clientID);
					item.IsAvailable = false;
					
					_gameManagerFactory
						.create(item.HostID, data.GuestID)
						.startGame(new IGameEndHandler()
						{
							@Override
							public void onGameEnd(Game game)
							{
								_games.remove(game.HareID);
							}
						});
				}
				
				_connectionManager.send(data, data.GuestID);
			}
		});
		
		_connectionManager.registerDataHandler(ClientDisconnectedInfo.class, new IDataHandler<ClientDisconnectedInfo>()
		{
			@Override
			public void handle(int clientID, ClientDisconnectedInfo data)
			{
				if (_games.containsKey(clientID))
				{
					_games.remove(clientID);
				}
			}
		});
		
		_connectionManager.startConnection();
	}
}

