package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import com.fARmework.RockPaperScissors.Data.GameResultInfo;
import com.fARmework.RockPaperScissors.Data.GestureInfo;
import com.fARmework.RockPaperScissors.Data.GameResultInfo.GameResult;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.RockPaperScissors.Server.Logic.IGestureProcessor;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.modules.ScreenGestures.Data.GestureData;

class Game
{
	public enum GameState
	{
		InProgress,
		Draw,
		HostWon,
		GuestWon
	}
	
	public IConnectionManager _connectionManager;
	public IGestureProcessor _gestureProcessor;
	
	private int _hostID;
	private int _guestID;
	private GestureType _hostGesture;
	private GestureType _guestGesture;
	
	public Game(IConnectionManager connectionManager, IGestureProcessor gestureProcessor, int hostID)
	{
		_connectionManager = connectionManager;
		_gestureProcessor = gestureProcessor;
		_hostID = hostID;
		
		_connectionManager.registerDataHandler(GestureInfo.class, new DataHandler<GestureInfo>()
		{
			@Override
			public void handleData(int clientID, GestureInfo data)
			{
				if (clientID == _hostID)
				{
					_hostGesture = data.GestureType;
				}
				else if (clientID == _guestID)
				{
					_guestGesture = data.GestureType;
				}
				else
				{
					return;
				}
				
				switch (getGameState())
				{
					case Draw:
						_connectionManager.send(new GameResultInfo(GameResult.Draw), _hostID);
						_connectionManager.send(new GameResultInfo(GameResult.Draw), _guestID);
						break;
					case HostWon:
						_connectionManager.send(new GameResultInfo(GameResult.Victory), _hostID);
						_connectionManager.send(new GameResultInfo(GameResult.Defeat), _guestID);
						break;
					case GuestWon:
						_connectionManager.send(new GameResultInfo(GameResult.Victory), _guestID);
						_connectionManager.send(new GameResultInfo(GameResult.Defeat), _hostID);
						break;
					default:
						break;
				}
			}
		});
		
		_connectionManager.registerDataHandler(GestureData.class, new DataHandler<GestureData>()
		{
			@Override
			public void handleData(int clientID, GestureData data)
			{
				_gestureProcessor.processGesture(data);
			}
		});
	}
	
	public int getHostID()
	{
		return _hostID;
	}
	
	public void setGuestID(int guestID)
	{
		_guestID = guestID;
	}
	
	private GameState getGameState()
	{
		if (_hostGesture == null || _guestGesture == null)
			return GameState.InProgress;
		else if (_hostGesture == _guestGesture)
			return GameState.Draw;
		else if (_hostGesture == GestureType.Rock && _guestGesture == GestureType.Scissors ||
				 _hostGesture == GestureType.Paper && _guestGesture == GestureType.Rock ||
				 _hostGesture == GestureType.Scissors && _guestGesture == GestureType.Paper)
			return GameState.HostWon;
		else
			return GameState.GuestWon;
	}
}