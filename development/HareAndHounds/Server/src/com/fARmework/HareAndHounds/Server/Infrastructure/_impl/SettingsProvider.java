package com.fARmework.HareAndHounds.Server.Infrastructure._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.utils.Java.*;
import com.google.inject.*;

public class SettingsProvider implements ISettingsProvider
{
	private static final String SETTINGS_FILE_NAME = "settings.xml";
	
	private final ISettingsReader _settingsReader;
	
	@Inject
	public SettingsProvider(ISettingsReader settingsReader)
	{
		_settingsReader = settingsReader;
		_settingsReader.loadSettings(SETTINGS_FILE_NAME);
	}
	
	@Override
	public int getPort()
	{
		return getInt("Port");
	}

	@Override
	public int getGameRange()
	{
		return getInt("GameRange");
	}

	@Override
	public int getCheckpointRange()
	{
		return getInt("CheckpointRange");
	}
	
	@Override
	public int getHareDemandedPositionUpdateInterval()
	{
		return getInt("HareDemandedPositionUpdateInterval");
	}

	@Override
	public int getHoundsDemandedPositionUpdateInterval()
	{
		return getInt("HoundsDemandedPositionUpdateInterval");
	}

	@Override
	public int getRequiredInitialHarePositions()
	{
		return getInt("RequiredInitialHarePositions");
	}

	@Override
	public int getVictoriousHarePositions()
	{
		return getInt("VictoriousHarePositions");
	}
	
	@Override
	public int getServerMode()
	{
		return getInt("ServerMode");
	}
	
	private int getInt(String key)
	{
		return Integer.valueOf(_settingsReader.get(key));		
	}
}
