package com.fARmework.RockPaperScissors.Server.Logic._impl;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GameJoinResponse.GameJoinResponseType;
import com.fARmework.RockPaperScissors.Data.GameResultInfo.GameResult;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.RockPaperScissors.Server.Gestures.GesturesData;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.core.server.Connection.IDataHandler;
import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;
import com.fARmework.modules.ScreenGestures.Java.IScreenGestureRecognizer;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;
import com.fARmework.modules.SpaceGestures.Java.ISpaceGestureRecognizer;

public class SingleGameManager implements ISingleGameManager
{
	private IConnectionManager _connectionManager;
	private IScreenGestureRecognizer _screenGestureRecognizer;
	private ISpaceGestureRecognizer _spaceGestureRecognizer;
	
	private Game _game;
	
	public SingleGameManager(IConnectionManager connectionManager, IScreenGestureRecognizer screenGestureRecognizer, ISpaceGestureRecognizer spaceGestureRecognizer, Game game)
	{
		_connectionManager = connectionManager;
		_screenGestureRecognizer = screenGestureRecognizer;
		_spaceGestureRecognizer = spaceGestureRecognizer;
		
		_game = game;
	}
	
	@Override
	public void handleJoin(GameJoinRequest request)
	{
		_connectionManager.registerDataHandler(GameJoinResponse.class, _game.HostID, new IDataHandler<GameJoinResponse>()
		{
			@Override
			public void handle(int clientID, GameJoinResponse data)
			{
				if (data.Response == GameJoinResponseType.Accept)
				{
					_game.GuestID = data.GuestID;
					_game.HasStarted = true;
					registerGestureHandlers();
					startGame();
				}
				
				_connectionManager.send(data, data.GuestID);
			}
		});
		
		_connectionManager.send(request, _game.HostID);
	}
	
	private void registerGestureHandlers()
	{
		_connectionManager.registerDataHandler(GestureInfo.class, _game.HostID, new IDataHandler<GestureInfo>()
		{
			@Override
			public void handle(int clientID, GestureInfo data)
			{
				_game.HostGesture = data.GestureType;
				handleGameState();
			}
		});
		
		_connectionManager.registerDataHandler(GestureInfo.class, _game.GuestID, new IDataHandler<GestureInfo>()
		{
			@Override
			public void handle(int clientID, GestureInfo data)
			{
				_game.GuestGesture = data.GestureType;
				handleGameState();
			}
		});
		
		_connectionManager.registerDataHandler(ScreenGestureData.class, _game.HostID, new IDataHandler<ScreenGestureData>()
		{
			@Override
			public void handle(int clientID, ScreenGestureData data)
			{
				GestureType gesture = GesturesData.getGestureType(_screenGestureRecognizer.recognize(data));
				
				_connectionManager.send(new GestureInfo(gesture), clientID);
				
				if (gesture != GestureType.Unknown)
				{
					_game.HostGesture = gesture;
					handleGameState();
				}
			}
		});
		
		_connectionManager.registerDataHandler(ScreenGestureData.class, _game.GuestID, new IDataHandler<ScreenGestureData>()
		{
			@Override
			public void handle(int clientID, ScreenGestureData data)
			{
				GestureType gesture = GesturesData.getGestureType(_screenGestureRecognizer.recognize(data));
				
				_connectionManager.send(new GestureInfo(gesture), clientID);
				
				if (gesture != GestureType.Unknown)
				{
					_game.GuestGesture = gesture;
					handleGameState();
				}
			}
		});
		
		_connectionManager.registerDataHandler(SpaceGestureData.class, _game.HostID, new IDataHandler<SpaceGestureData>()
		{
			@Override
			public void handle(int clientID, SpaceGestureData data)
			{
				GestureType gesture = GesturesData.getGestureType(_spaceGestureRecognizer.recognize(data));
				
				_connectionManager.send(new GestureInfo(gesture), clientID);
				
				if (gesture != GestureType.Unknown)
				{
					_game.HostGesture = gesture;
					handleGameState();
				}
			}
		});
		
		_connectionManager.registerDataHandler(SpaceGestureData.class, _game.GuestID, new IDataHandler<SpaceGestureData>()
		{
			@Override
			public void handle(int clientID, SpaceGestureData data)
			{
				GestureType gesture = GesturesData.getGestureType(_spaceGestureRecognizer.recognize(data));
				
				_connectionManager.send(new GestureInfo(gesture), clientID);
				
				if (gesture != GestureType.Unknown)
				{
					_game.GuestGesture = gesture;
					handleGameState();
				}
			}
		});
		
		_connectionManager.send(new GameStartInfo(), _game.HostID);
		_connectionManager.send(new GameStartInfo(), _game.GuestID);
	}

	private void startGame()
	{
		_game.HostWantsNextGame = null;
		_game.GuestWantsNextGame = null;
		_connectionManager.send(new GameStartInfo(), _game.HostID);
		_connectionManager.send(new GameStartInfo(), _game.GuestID);
	}
	
	private void handleGameState()
	{
		if (_game.HostGesture == null || _game.GuestGesture == null) // game still in progress
		{
			return;
		}
		
		_connectionManager.registerDataHandler(NextGameInfo.class, _game.HostID, new IDataHandler<NextGameInfo>()
		{
			@Override
			public void handle(int clientID, NextGameInfo data)
			{
				_game.HostWantsNextGame = data.WantsNextGame;
				
				if (data.WantsNextGame)
				{
					if (_game.GuestWantsNextGame != null && _game.GuestWantsNextGame.booleanValue())
					{
						startGame();
					}
				}
				else
				{
					_game.HasEnded = true;
					_connectionManager.send(new GameEndInfo(), _game.GuestID);
				}
			}
		});
		
		_connectionManager.registerDataHandler(NextGameInfo.class, _game.GuestID, new IDataHandler<NextGameInfo>()
		{
			@Override
			public void handle(int clientID, NextGameInfo data)
			{
				_game.GuestWantsNextGame = data.WantsNextGame;
				
				if (data.WantsNextGame)
				{
					if (_game.HostWantsNextGame != null && _game.HostWantsNextGame.booleanValue())
					{
						startGame();
					}
				}
				else
				{
					_game.HasEnded = true;
					_connectionManager.send(new GameEndInfo(), _game.HostID);
				}
			}
		});
		
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
		
		_game.HostGesture = null;
		_game.GuestGesture = null;
	}
}
