package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.google.inject.*;

public class GameListFactory implements IGameListFactory
{
	private ISettingsProvider _settingsProvider;
	private ISimulatorDataProvider _simulatorDataProvider;
	
	@Inject
	public GameListFactory(ISettingsProvider settingsProvider, ISimulatorDataProvider simulatorDataProvider)
	{
		_settingsProvider = settingsProvider;
		_simulatorDataProvider = simulatorDataProvider;
	}

	@Override
	public IGameList create()
	{
		if (_settingsProvider.getServerMode() == ServerModes.SIMULATE_HARE_MODE)
		{
			return new SimulatorGameList(_simulatorDataProvider);
		}
		
		return new GameList();
	}
}
