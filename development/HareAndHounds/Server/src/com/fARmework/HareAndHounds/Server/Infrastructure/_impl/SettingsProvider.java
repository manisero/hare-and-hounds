package com.fARmework.HareAndHounds.Server.Infrastructure._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.utils.Java.*;
import com.google.inject.*;

public class SettingsProvider implements ISettingsProvider
{
	private static final String SETTINGS_FILE_NAME = "settings.xml";
	
	ISettingsReader _settingsReader;
	
	@Inject
	public SettingsProvider(ISettingsReader settingsReader)
	{
		_settingsReader = settingsReader;
		_settingsReader.setSettingsFileName(SETTINGS_FILE_NAME);
	}
	
	@Override
	public int getPort()
	{
		return Integer.valueOf(_settingsReader.get("Port"));
	}

	@Override
	public int getGameRange()
	{
		return 1000;
	}

	@Override
	public int getCheckpointRange()
	{
		return 20;
	}
	
	@Override
	public int getHareDemandedPositionUpdateInterval()
	{
		return 20;
	}

	@Override
	public int getHoundsDemandedPositionUpdateInterval()
	{
		return 10;
	}

	@Override
	public int getRequiredInitialHarePositions()
	{
		return 3;
	}

	@Override
	public int getVictoriousHarePositions()
	{
		return 30;
	}
}
