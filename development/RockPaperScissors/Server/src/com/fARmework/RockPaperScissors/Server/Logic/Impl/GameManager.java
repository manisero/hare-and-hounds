package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.google.inject.Inject;

public class GameManager implements IGameManager
{
	private IConnectionManager _connectionManager;
	private IGestureProcessor _gestureProcessor;
	
	private Map<Integer, Game> _games = new LinkedHashMap<Integer, Game>();
	
	@Inject
	public GameManager(IConnectionManager connectionManager, IGestureProcessor gestureProcessor)
	{
		_connectionManager = connectionManager;
		_gestureProcessor = gestureProcessor;
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
				_games.put(clientID, new Game(_connectionManager, _gestureProcessor, clientID));
				_connectionManager.send(new GameCreationInfo(), clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameListRequest.class, new DataHandler<GameListRequest>()
		{
			@Override
			public void handleData(int clientID, GameListRequest data)
			{
				LinkedList<Integer> hostIDs = new LinkedList<Integer>();
				
				for (Game game : _games.values())
				{
					hostIDs.add(game.getHostID());
				}
				
				_connectionManager.send(new GameListData(hostIDs), clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameJoinRequest.class, new DataHandler<GameJoinRequest>()
		{
			@Override
			public void handleData(int clientID, GameJoinRequest data)
			{
				_games.get(data.HostID).setGuestID(clientID);
				_connectionManager.send(new GameStartInfo());
			}
		});
	}
}
