package com.fARmework.RockPaperScissors.Server.Infrastructure;

import com.fARmework.core.server.Infrastructure.ISettingsProvider;
import com.fARmework.utils.Java.*;
import com.google.inject.*;

@Singleton
public class SettingsProvider implements ISettingsProvider
{
	private static final String SETTINGS_FILE_NAME = "settings.xml";
	
	private ISettingsReader _settingsReader;
	
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
	
	private int getInt(String key)
	{
		return Integer.valueOf(_settingsReader.get(key));		
	}	
}
