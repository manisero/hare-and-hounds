package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Java.*;
import com.google.inject.*;

public class GameManagerFactory implements IGameManagerFactory
{
	private final IConnectionManager _connectionManager;
	private final ISettingsProvider _settingsProvider;
	private final IDirectionCalculator _directionCalculator;
	private final IDistanceCalculator _distanceCalculator;
	
	@Inject
	public GameManagerFactory(IConnectionManager connectionManager, ISettingsProvider settingsProvider, IDirectionCalculator directionCalculator, IDistanceCalculator distanceCalculator)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
		_directionCalculator = directionCalculator;
		_distanceCalculator = distanceCalculator;
	}
	
	@Override
	public IGameManager create(int hareID, int houndsID)
	{
		if (_settingsProvider.getServerMode() == ServerModes.SIMULATE_HARE_MODE)
		{
			return new SimulatorGameManager(_connectionManager, _settingsProvider, _directionCalculator, _distanceCalculator, houndsID);
		}
		
		return new GameManager(_connectionManager, _settingsProvider, _directionCalculator, _distanceCalculator, hareID, houndsID);
	}
}
