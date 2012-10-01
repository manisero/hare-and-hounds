package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Data.JoinGameResponse.JoinGameResponseType;
import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.HareAndHounds.Server.Logic.IGameManager.IGameEndHandler;
import com.fARmework.core.server.Connection.*;
import com.fARmework.core.server.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;
import com.google.inject.*;

public class GameListManager implements IGameListManager
{	
	private final IConnectionManager _connectionManager;
	private final IDistanceCalculator _distanceCalculator;
	private final IGameManagerFactory _gameManagerFactory;
	private final ISettingsProvider _settingsProvider;
	private final IGameList _gameList;
	
	@Inject
	public GameListManager(IConnectionManager connectionManager, IDistanceCalculator distanceCalculator, IGameManagerFactory gameManagerFactory, ISettingsProvider settingsProvider, IGameList gameList)
	{
		_connectionManager = connectionManager;
		_distanceCalculator = distanceCalculator;
		_gameManagerFactory = gameManagerFactory;
		_settingsProvider = settingsProvider;
		_gameList = gameList;
	}
	
	@Override
	public void run()
	{
		_connectionManager.registerDataHandler(NewGameRequest.class, new IDataHandler<NewGameRequest>()
		{
			@Override
			public void handle(int clientID, NewGameRequest data)
			{
				if (!_gameList.containsKey(clientID))
				{
					_gameList.put(clientID, new GameListItem(clientID, data.HostName, data.Position));
				}
				else
				{
					_gameList.get(clientID).HostPosition = data.Position;
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
				
				for (GameListItem item : _gameList.values())
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
				if (!_gameList.containsKey(data.HostID) || !_gameList.get(data.HostID).IsAvailable)
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
					GameListItem item = _gameList.get(clientID);
					item.IsAvailable = false;
					
					_gameManagerFactory
						.create(item.HostID, data.GuestID)
						.startGame(new IGameEndHandler()
						{
							@Override
							public void onGameEnd(int hostID, int guestID)
							{
								_gameList.remove(hostID);
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
				if (_gameList.containsKey(clientID))
				{
					_gameList.remove(clientID);
				}
			}
		});
		
		_connectionManager.startConnection();
	}
}

