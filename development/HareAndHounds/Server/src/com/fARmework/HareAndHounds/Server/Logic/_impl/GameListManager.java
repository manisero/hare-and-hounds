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
	private final IGameListFactory _gameListFactory;
	private final ISettingsProvider _settingsProvider;
	
	@Inject
	public GameListManager(IConnectionManager connectionManager, IDistanceCalculator distanceCalculator, IGameManagerFactory gameManagerFactory, IGameListFactory gameListFactory, ISettingsProvider settingsProvider)
	{
		_connectionManager = connectionManager;
		_distanceCalculator = distanceCalculator;
		_gameManagerFactory = gameManagerFactory;
		_gameListFactory = gameListFactory;
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void run()
	{
		final IGameList gameList = _gameListFactory.create();
		
		_connectionManager.registerDataHandler(NewGameRequest.class, new IDataHandler<NewGameRequest>()
		{
			@Override
			public void handle(int clientID, NewGameRequest data)
			{
				if (!gameList.containsKey(clientID))
				{
					gameList.put(clientID, new GameListItem(clientID, data.HostName, data.Position));
				}
				else
				{
					gameList.get(clientID).HostPosition = data.Position;
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
				
				for (GameListItem item : gameList.values())
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
				if (!gameList.containsKey(data.HostID) || !gameList.get(data.HostID).IsAvailable)
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
					GameListItem item = gameList.get(clientID);
					item.IsAvailable = false;
					
					_gameManagerFactory
						.create(item.HostID, data.GuestID)
						.startGame(new IGameEndHandler()
						{
							@Override
							public void onGameEnd(int hostID, int guestID)
							{
								gameList.remove(hostID);
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
				if (gameList.containsKey(clientID))
				{
					gameList.remove(clientID);
				}
			}
		});
		
		_connectionManager.startConnection();
	}
}

