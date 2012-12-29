package com.fARmework.RockPaperScissors.Server.Logic._impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.core.server.Connection.IDataHandler;
import com.fARmework.core.server.Data.ClientDisconnectedInfo;
import com.google.inject.Inject;

public class GamesManager implements IGamesManager
{
	private final IConnectionManager _connectionManager;
	private final ISingleGameManagerFactory _gameManagerFactory;
	
	private Map<Integer, Game> _games = new LinkedHashMap<Integer, Game>();
	
	@Inject
	public GamesManager(IConnectionManager connectionManager, ISingleGameManagerFactory gameManagerFactory)
	{
		_connectionManager = connectionManager;
		_gameManagerFactory = gameManagerFactory;
	}
	
	@Override
	public void run()
	{
		_connectionManager.startConnection();
		
		_connectionManager.registerDataHandler(GameCreationRequest.class, new IDataHandler<GameCreationRequest>()
		{
			@Override
			public void handle(int clientID, GameCreationRequest data)
			{
				Game newGame = new Game();
				newGame.HostID = clientID;
				newGame.HostUserName = data.HostUserName;
				_games.put(clientID, newGame);
				
				_connectionManager.send(new GameCreationResponse(), clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameListRequest.class, new IDataHandler<GameListRequest>()
		{
			@Override
			public void handle(int clientID, GameListRequest data)
			{
				GameListData gameList = new GameListData();
				
				for (Game game : _games.values())
				{
					if (!game.HasStarted)
					{
						gameList.addGame(game.HostID, game.HostUserName);
					}
				}
				
				_connectionManager.send(gameList, clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameJoinRequest.class, new IDataHandler<GameJoinRequest>()
		{
			@Override
			public void handle(int clientID, GameJoinRequest data)
			{
				if (!_games.containsKey(data.HostID) || _games.get(data.HostID).HasStarted)
				{
					_connectionManager.send(new GameJoinResponse(clientID, GameJoinResponseType.NotAvailable), clientID);
					return;
				}
				
				data.GuestID = clientID;
				_gameManagerFactory.create(_games.get(data.HostID)).handleJoin(data);
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
	}
}
