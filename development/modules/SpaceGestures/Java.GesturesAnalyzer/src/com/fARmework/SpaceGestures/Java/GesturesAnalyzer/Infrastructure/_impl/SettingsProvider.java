package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Infrastructure._impl;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Infrastructure.ISettingsProvider;
import com.fARmework.utils.Java.ISettingsReader;

public class SettingsProvider implements ISettingsProvider
{
	private static final String SETTINGS_FILE_NAME = "settings.xml";
	
	private ISettingsReader _settingsReader;
	
	public SettingsProvider(ISettingsReader settingsReader)
	{
		_settingsReader = settingsReader;
		_settingsReader.loadSettings(SETTINGS_FILE_NAME);
	}

	@Override
	public boolean getHasTimestamps()
	{
		return getBoolean("HasTimestamps");
	}

	@Override
	public String getFileExtension()
	{
		return _settingsReader.get("FileExtension");
	}

	@Override
	public String getRelativePath()
	{
		return _settingsReader.get("RelativePath");
	}
	
	private boolean getBoolean(String key)
	{
		return Boolean.valueOf(_settingsReader.get(key));		
	}	
}
