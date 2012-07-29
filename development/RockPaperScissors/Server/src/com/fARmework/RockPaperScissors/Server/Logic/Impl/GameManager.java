package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedList;

import com.fARmework.RockPaperScissors.Data.CreateGameRequest;
import com.fARmework.RockPaperScissors.Data.CreateGameResponse;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListResponse;
import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.IDataHandler;
import com.fARmework.RockPaperScissors.Server.Logic.IGameManager;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.google.inject.Inject;

public class GameManager implements IGameManager
{
	private class Game
	{
		private int _hostID;
		private int _guestID;
		
		public Game(int hostID)
		{
			_hostID = hostID;
		}
		
		public int getHostID()
		{
			return _hostID;
		}
		
		public void setHostID(int hostID)
		{
			_hostID = hostID;
		}

		public int getGuestID()
		{
			return _guestID;
		}

		public void setGuestID(int guestID)
		{
			_guestID = guestID;
		}
	}
	
	private IConnectionManager _connectionManager;
	private IConnectionHandler _connectionHandler;
	
	private LinkedList<Game> _games = new LinkedList<Game>();
	
	@Inject
	public GameManager(IConnectionManager connectionManager, IConnectionHandler connectionHandler)
	{
		_connectionManager = connectionManager;
		_connectionHandler = connectionHandler;
	}
	
	@Override
	public void run()
	{
		_connectionManager.startConnection(_connectionHandler);
		
		_connectionHandler.registerHandler(CreateGameRequest.class, new IDataHandler<CreateGameRequest>()
		{
			@Override
			public void handle(int clientID, CreateGameRequest data)
			{
				_games.add(new Game(clientID));
				_connectionManager.send(new CreateGameResponse(), clientID);
			}
		});
		
		_connectionHandler.registerHandler(GameListRequest.class, new IDataHandler<GameListRequest>()
		{
			@Override
			public void handle(int clientID, GameListRequest data)
			{
				LinkedList<Integer> hostIDs = new LinkedList<Integer>();
				
				for (Game game : _games)
				{
					hostIDs.add(game.getHostID());
				}
				
				_connectionManager.send(new GameListResponse(hostIDs), clientID);
			}
		});
	}

}
