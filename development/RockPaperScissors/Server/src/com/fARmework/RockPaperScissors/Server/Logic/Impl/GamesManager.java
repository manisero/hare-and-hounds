package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.google.inject.Inject;

public class GamesManager implements IGamesManager
{
	private IConnectionManager _connectionManager;
	private ISingleGameManagerFactory _gameManagerFactory;
	
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
		
		_connectionManager.registerDataHandler(GameCreationRequest.class, new DataHandler<GameCreationRequest>()
		{
			@Override
			public void handleData(int clientID, GameCreationRequest data)
			{
				System.out.println("HostUserName: " + data.HostUserName);
				
				Game newGame = new Game();
				newGame.HostID = clientID;
				newGame.HostUserName = data.HostUserName;
				_games.put(clientID, newGame);
				
				_connectionManager.send(new GameCreationInfo(), clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameListRequest.class, new DataHandler<GameListRequest>()
		{
			@Override
			public void handleData(int clientID, GameListRequest data)
			{
				GameListData gameList = new GameListData();
				
				for (Game game : _games.values())
				{
					gameList.addGame(game.HostID, game.HostUserName);
				}
				
				_connectionManager.send(gameList, clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameJoinData.class, new DataHandler<GameJoinData>()
		{
			@Override
			public void handleData(int clientID, GameJoinData data)
			{
				System.out.println("HostID: " + data.HostID);
				System.out.println("GuestUserName: " + data.GuestUserName);
				
				_gameManagerFactory.create(_games.get(data.HostID)).handleJoin(clientID, data.GuestUserName);
			}
		});
	}
}
