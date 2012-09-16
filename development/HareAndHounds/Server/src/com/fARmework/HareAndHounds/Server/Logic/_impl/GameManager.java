package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class GameManager implements IGameManager
{
	private IConnectionManager _connectionManager;
	private ISettingsProvider _settingsProvider;
	
	private int _hareID;
	private int _houndsID;
	private PositionDataList _harePositions;
	
	public GameManager(IConnectionManager connectionManager, ISettingsProvider settingsProvider, IDistanceCalculator distanceCalculator, int hareID, int houndsID)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
		
		_hareID = hareID;
		_houndsID = houndsID;
		_harePositions = new PositionDataList(distanceCalculator);
	}
	
	@Override
	public void startGame(IGameEndHandler gameEndHandler)
	{
		_connectionManager.registerDataHandler(PositionData.class, _hareID, new IDataHandler<PositionData>()
		{
			@Override
			public void handle(int clientID, PositionData data)
			{
				_harePositions.add(data);
			}
		});
		
		_connectionManager.registerDataHandler(PositionData.class, _houndsID, new IDataHandler<PositionData>()
		{
			@Override
			public void handle(int clientID, PositionData data)
			{
				if (_harePositions.size() < _settingsProvider.getDemandedInitialHarePositions())
				{
					return;
				}
				
				// TODO: implement
			}
		});
		
		_connectionManager.send(new GameStartInfo(_settingsProvider.getHareDemandedPositionUpdateInterval()), _hareID);
		_connectionManager.send(new GameStartInfo(_settingsProvider.getHoundsDemandedPositionUpdateInterval()), _houndsID);
	}
}
