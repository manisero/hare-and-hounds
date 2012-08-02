package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GameResultInfo.GameResult;
import com.fARmework.RockPaperScissors.Data.GestureData.GestureType;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.EmptyDataHandler;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.core.server.Data.ClientConnectedInfo;
import com.fARmework.core.server.Data.ClientDisconnectedInfo;
import com.fARmework.core.server.Data.ConnectionExceptionInfo;
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
	
	private Map<Integer, Game> _games = new LinkedHashMap<Integer, Game>();
	
	@Inject
	public GameManager(IConnectionManager connectionManager)
	{
		_connectionManager = connectionManager;
	}
	
	@Override
	public void run()
	{
		_connectionManager.startConnection();
		
		_connectionManager.registerDataHandler(ClientConnectedInfo.class, new EmptyDataHandler<ClientConnectedInfo>());
		_connectionManager.registerDataHandler(ClientDisconnectedInfo.class, new EmptyDataHandler<ClientDisconnectedInfo>());
		_connectionManager.registerDataHandler(ConnectionExceptionInfo.class, new DataHandler<ConnectionExceptionInfo>()
		{
			@Override
			public void handle(int clientID, ConnectionExceptionInfo data)
			{
				super.handle(clientID, data);
				System.out.println("Exception: " + data.Exception.getMessage());
			}
		});
		
		_connectionManager.registerDataHandler(GameCreationRequest.class, new DataHandler<GameCreationRequest>()
		{
			@Override
			public void handle(int clientID, GameCreationRequest data)
			{
				super.handle(clientID, data);
				_games.put(clientID, new Game(clientID));
				_connectionManager.send(new GameCreationInfo(), clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameListRequest.class, new DataHandler<GameListRequest>()
		{
			@Override
			public void handle(int clientID, GameListRequest data)
			{
				super.handle(clientID, data);
				
				LinkedList<Integer> hostIDs = new LinkedList<Integer>();
				
				for (Game game : _games.values())
				{
					hostIDs.add(game.HostID);
				}
				
				_connectionManager.send(new GameListData(hostIDs), clientID);
			}
		});
		
		_connectionManager.registerDataHandler(GameJoinRequest.class, new DataHandler<GameJoinRequest>()
		{
			@Override
			public void handle(int clientID, GameJoinRequest data)
			{
				super.handle(clientID, data);
				_games.get(data.HostID).GuestID = clientID;
				_connectionManager.send(new GameStartInfo());
			}
		});
		
		_connectionManager.registerDataHandler(GestureData.class, new DataHandler<GestureData>()
		{
			@Override
			public void handle(int clientID, GestureData data)
			{
				super.handle(clientID, data);
				
				Game currentGame = null;
				
				for (Game game : _games.values())
				{
					if (clientID == game.HostID)
					{
						game.HostGesture = data.GestureType;
						currentGame = game;
						break;
					}
					else if (clientID == game.GuestID)
					{
						game.GuestGesture = data.GestureType;
						currentGame = game;
						break;
					}
				}
				
				switch (currentGame.getGameState())
				{
					case Draw:
						_connectionManager.send(new GameResultInfo(GameResult.Draw), currentGame.HostID);
						_connectionManager.send(new GameResultInfo(GameResult.Draw), currentGame.GuestID);
						break;
					case HostWon:
						_connectionManager.send(new GameResultInfo(GameResult.Victory), currentGame.HostID);
						_connectionManager.send(new GameResultInfo(GameResult.Defeat), currentGame.GuestID);
						break;
					case GuestWon:
						_connectionManager.send(new GameResultInfo(GameResult.Victory), currentGame.GuestID);
						_connectionManager.send(new GameResultInfo(GameResult.Defeat), currentGame.HostID);
						break;
					default:
						break;
				}
			}
		});
	}

}
