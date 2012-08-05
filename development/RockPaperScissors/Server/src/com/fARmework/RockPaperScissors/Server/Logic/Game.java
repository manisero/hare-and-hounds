package com.fARmework.RockPaperScissors.Server.Logic;

import com.fARmework.RockPaperScissors.Data.GameResultInfo;
import com.fARmework.RockPaperScissors.Data.GameStartInfo;
import com.fARmework.RockPaperScissors.Data.GestureInfo;
import com.fARmework.RockPaperScissors.Data.GameResultInfo.GameResult;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.modules.ScreenGestures.Data.GestureData;

public class Game
{
	public IConnectionManager _connectionManager;
	public IGestureProcessor _gestureProcessor;
	
	private int _hostID;
	private int _guestID;
	private String _hostUserName;
	private GestureType _hostGesture;
	private GestureType _guestGesture;
	
	public Game(IConnectionManager connectionManager, IGestureProcessor gestureProcessor, int hostID, String hostUserName)
	{
		_connectionManager = connectionManager;
		_gestureProcessor = gestureProcessor;
		
		_hostID = hostID;
		_hostUserName = hostUserName;
	}
	
	public int getHostID()
	{
		return _hostID;
	}
	
	public String getHostUserName()
	{
		return _hostUserName;
	}
	
	public void start(int guestID)
	{
		_guestID = guestID;
		
		_connectionManager.registerDataHandler(GestureInfo.class, _hostID, new DataHandler<GestureInfo>()
		{
			@Override
			public void handleData(int clientID, GestureInfo data)
			{
				_hostGesture = data.GestureType;
				handleGameState();
			}
		});
		
		_connectionManager.registerDataHandler(GestureInfo.class, _guestID, new DataHandler<GestureInfo>()
		{
			@Override
			public void handleData(int clientID, GestureInfo data)
			{
				_guestGesture = data.GestureType;
				handleGameState();
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
		
		_connectionManager.send(new GameStartInfo(), _hostID);
		_connectionManager.send(new GameStartInfo(), _guestID);
	}
	
	private void handleGameState()
	{
		if (_hostGesture == null || _guestGesture == null) // game still in progress
		{
			return;
		}
		else if (_hostGesture == _guestGesture) // draw
		{
			_connectionManager.send(new GameResultInfo(GameResult.Draw), _hostID);
			_connectionManager.send(new GameResultInfo(GameResult.Draw), _guestID);
		}
		else if (_hostGesture == GestureType.Rock && _guestGesture == GestureType.Scissors ||
				 _hostGesture == GestureType.Paper && _guestGesture == GestureType.Rock ||
				 _hostGesture == GestureType.Scissors && _guestGesture == GestureType.Paper) // host won
		{
			_connectionManager.send(new GameResultInfo(GameResult.Victory), _hostID);
			_connectionManager.send(new GameResultInfo(GameResult.Defeat), _guestID);
		}
		else // guest won
		{
			_connectionManager.send(new GameResultInfo(GameResult.Victory), _guestID);
			_connectionManager.send(new GameResultInfo(GameResult.Defeat), _hostID);
		}
	}
}
