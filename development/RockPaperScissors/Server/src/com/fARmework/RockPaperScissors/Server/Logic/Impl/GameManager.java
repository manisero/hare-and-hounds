package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GestureData.GestureType;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.google.inject.Inject;

public class GameManager implements IGameManager
{
	public enum GameState
	{
		InProgress,
		Draw,
		HostWon,
		GuestWon
	}
	
	private class Game
	{
		public int HostID;
		public int GuestID;
		public GestureType HostGesture;
		public GestureType GuestGesture;
		
		public Game(int hostID)
		{
			this.HostID = hostID;
		}
		
		public GameState getGameState()
		{
			if (HostGesture == null || GuestGesture == null)
				return GameState.InProgress;
			else if (HostGesture == GuestGesture)
				return GameState.Draw;
			else if (HostGesture == GestureType.Rock && GuestGesture == GestureType.Scissors ||
					 HostGesture == GestureType.Paper && GuestGesture == GestureType.Rock ||
					 HostGesture == GestureType.Scissors && GuestGesture == GestureType.Paper)
				return GameState.HostWon;
			else
				return GameState.GuestWon;
		}
	}
	
	private IConnectionManager _connectionManager;
	private IConnectionHandler _connectionHandler;
	
	private Map<Integer, Game> _games = new LinkedHashMap<Integer, Game>();
	
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
				_games.put(clientID, new Game(clientID));
				_connectionManager.send(new CreateGameResponse(), clientID);
			}
		});
		
		_connectionHandler.registerHandler(GameListRequest.class, new IDataHandler<GameListRequest>()
		{
			@Override
			public void handle(int clientID, GameListRequest data)
			{
				LinkedList<Integer> hostIDs = new LinkedList<Integer>();
				
				for (Game game : _games.values())
				{
					hostIDs.add(game.HostID);
				}
				
				_connectionManager.send(new GameListResponse(hostIDs), clientID);
			}
		});
		
		_connectionHandler.registerHandler(JoinGameRequest.class, new IDataHandler<JoinGameRequest>()
		{
			@Override
			public void handle(int clientID, JoinGameRequest data)
			{
				_games.get(data.getHostID()).GuestID = clientID;
				_connectionManager.send(new GameStartInfo());
			}
		});
		
		_connectionHandler.registerHandler(GestureData.class, new IDataHandler<GestureData>()
		{
			@Override
			public void handle(int clientID, GestureData data)
			{
				Game currentGame = null;
				
				for (Game game : _games.values())
				{
					if (clientID == game.HostID)
					{
						game.HostGesture = data.getGestureType();
						currentGame = game;
						break;
					}
					else if (clientID == game.GuestID)
					{
						game.GuestGesture = data.getGestureType();
						currentGame = game;
						break;
					}
				}
				
				switch (currentGame.getGameState())
				{
					case Draw:
						_connectionManager.send(new DrawInfo(), currentGame.HostID);
						_connectionManager.send(new DrawInfo(), currentGame.GuestID);
						break;
					case HostWon:
						_connectionManager.send(new VictoryInfo(), currentGame.HostID);
						_connectionManager.send(new DefeatInfo(), currentGame.GuestID);
						break;
					case GuestWon:
						_connectionManager.send(new VictoryInfo(), currentGame.GuestID);
						_connectionManager.send(new DefeatInfo(), currentGame.HostID);
						break;
					default:
						break;
				}
			}
		});
	}

}
