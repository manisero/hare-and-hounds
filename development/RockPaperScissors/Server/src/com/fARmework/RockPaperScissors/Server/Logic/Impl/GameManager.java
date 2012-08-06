package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.google.inject.Inject;

public class GameManager implements IGameManager
{
	private IConnectionManager _connectionManager;
	private IGameFactory _gameFactory;
	
	private Map<Integer, Game> _games = new LinkedHashMap<Integer, Game>();
	
	@Inject
	public GameManager(IConnectionManager connectionManager, IGameFactory gameFactory)
	{
		_connectionManager = connectionManager;
		_gameFactory = gameFactory;
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
				
				_games.put(clientID, _gameFactory.createGame(clientID, data.HostUserName));
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
					gameList.addGame(game.getHostID(), game.getHostUserName());
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
				
				_connectionManager.send(new GameJoinRequest(data.HostID, clientID, data.GuestUserName), data.HostID);
				_games.get(data.HostID).start(clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameJoinResponse.class, new DataHandler<GameJoinResponse>()
		{
			@Override
			protected void handleData(int clientID, GameJoinResponse data)
			{
				System.out.println("HostID: " + data.HostID);
				System.out.println("GuestID: " + data.GuestID);
				System.out.println("Response: " + data.Response.toString());
				
				if (data.Response == GameJoinResponseType.Accept)
				{
					_games.get(data.HostID).start(data.GuestID);
				}
				
				_connectionManager.send(data, data.GuestID);
			}
		});
	}
}
