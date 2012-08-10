package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GameResultInfo.GameResult;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.modules.ScreenGestures.Data.GestureData;

public class SingleGameManager implements ISingleGameManager
{
	private IConnectionManager _connectionManager;
	private IGestureProcessor _gestureProcessor;
	
	private Game _game;
	
	public SingleGameManager(IConnectionManager connectionManager, IGestureProcessor gestureProcessor, Game game)
	{
		_connectionManager = connectionManager;
		_gestureProcessor = gestureProcessor;
		
		_game = game;
	}
	
	@Override
	public void handleJoin(int guestID, String guestUserName)
	{
		_connectionManager.registerDataHandler(GameJoinResponse.class, _game.HostID, new DataHandler<GameJoinResponse>()
		{
			@Override
			protected void handleData(int clientID, GameJoinResponse data)
			{
				System.out.println("GuestID: " + data.GuestID);
				System.out.println("Accepted: " + String.valueOf(data.Accepted));
				
				if (data.Accepted)
				{
					_game.GuestID = data.GuestID;
					startGame();
				}
				
				_connectionManager.send(data, data.GuestID);
			}
		});
		
		_connectionManager.send(new GameJoinRequest(_game.HostID, guestID, guestUserName), _game.HostID);
	}
	
	private void startGame()
	{
		_game.HasStarted = true;
		
		_connectionManager.registerDataHandler(GestureInfo.class, _game.HostID, new DataHandler<GestureInfo>()
		{
			@Override
			public void handleData(int clientID, GestureInfo data)
			{
				_game.HostGesture = data.GestureType;
				handleGameState();
			}
		});
		
		_connectionManager.registerDataHandler(GestureInfo.class, _game.GuestID, new DataHandler<GestureInfo>()
		{
			@Override
			public void handleData(int clientID, GestureInfo data)
			{
				_game.GuestGesture = data.GestureType;
				handleGameState();
			}
		});
		
		_connectionManager.registerDataHandler(GestureData.class, new DataHandler<GestureData>()
		{
			@Override
			public void handleData(int clientID, GestureData data)
			{
				// TODO: Finish implementing when gesture recognition is finished
				_gestureProcessor.processGesture(data);
			}
		});
	}

	private void handleGameState()
	{
		if (_game.HostGesture == null || _game.GuestGesture == null) // game still in progress
		{
			return;
		}
		
		if (_game.HostGesture == _game.GuestGesture) // draw
		{
			_connectionManager.send(new GameResultInfo(_game.HostGesture, _game.GuestGesture, GameResult.Draw, _game.HostScore, _game.GuestScore), _game.HostID);
			_connectionManager.send(new GameResultInfo(_game.GuestGesture, _game.HostGesture, GameResult.Draw, _game.GuestScore, _game.HostScore), _game.GuestID);
		}
		else if (_game.HostGesture == GestureType.Rock && _game.GuestGesture == GestureType.Scissors ||
				_game.HostGesture == GestureType.Paper && _game.GuestGesture == GestureType.Rock ||
				_game.HostGesture == GestureType.Scissors && _game.GuestGesture == GestureType.Paper) // host won
		{
			_game.HostScore++;
			_connectionManager.send(new GameResultInfo(_game.HostGesture, _game.GuestGesture, GameResult.Victory, _game.HostScore, _game.GuestScore), _game.HostID);
			_connectionManager.send(new GameResultInfo(_game.GuestGesture, _game.HostGesture, GameResult.Defeat, _game.GuestScore, _game.HostScore), _game.GuestID);
		}
		else // guest won
		{
			_game.GuestScore++;
			_connectionManager.send(new GameResultInfo(_game.HostGesture, _game.GuestGesture, GameResult.Defeat, _game.HostScore, _game.GuestScore), _game.HostID);
			_connectionManager.send(new GameResultInfo(_game.GuestGesture, _game.HostGesture, GameResult.Victory, _game.GuestScore, _game.HostScore), _game.GuestID);
		}
		
		_connectionManager.registerDataHandler(NextGameInfo.class, _game.HostID, new DataHandler<NextGameInfo>()
		{
			@Override
			protected void handleData(int clientID, NextGameInfo data)
			{
				if (!data.WantsNextGame)
				{
					_game.HasEnded = true;
				}
				
				_connectionManager.send(data, _game.GuestID);
			}
		});
		
		_connectionManager.registerDataHandler(NextGameInfo.class, _game.GuestID, new DataHandler<NextGameInfo>()
		{
			@Override
			protected void handleData(int clientID, NextGameInfo data)
			{
				if (!data.WantsNextGame)
				{
					_game.HasEnded = true;
				}
				
				_connectionManager.send(data, _game.HostID);
			}
		});
	}
}
