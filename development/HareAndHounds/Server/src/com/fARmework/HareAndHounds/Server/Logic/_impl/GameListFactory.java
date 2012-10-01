package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.google.inject.*;

public class GameListFactory implements IGameListFactory
{
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public GameListFactory(ISettingsProvider settingsProvider)
	{
		_settingsProvider = settingsProvider;
	}

	@Override
	public IGameList create()
	{
		return new GameList();
	}
}
