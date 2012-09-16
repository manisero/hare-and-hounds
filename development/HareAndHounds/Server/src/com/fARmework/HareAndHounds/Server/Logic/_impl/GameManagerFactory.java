package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Java.*;
import com.google.inject.*;

public class GameManagerFactory implements IGameManagerFactory
{
	private IConnectionManager _connectionManager;
	private ISettingsProvider _settingsProvider;
	private IDistanceCalculator _distanceCalculator;
	
	@Inject
	public GameManagerFactory(IConnectionManager connectionManager, ISettingsProvider settingsProvider, IDistanceCalculator distanceCalculator)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
		_distanceCalculator = distanceCalculator;
	}
	
	@Override
	public IGameManager create(int hareID, int houndsID)
	{
		return new GameManager(_connectionManager, _settingsProvider, _distanceCalculator, hareID, houndsID);
	}
}
