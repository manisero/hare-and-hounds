package com.fARmework.RockPaperScissors.Client.Infrastructure;

import android.content.res.Resources;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.core.client.Infrastructure.ISettingsProvider;
import com.google.inject.Singleton;

@Singleton
public class SettingsProvider implements ISettingsProvider
{
	public static Resources _settings;
	
	public void setResources(Resources settings)
	{
		_settings = settings;
	}
	
	@Override
	public String serverAddress()
	{
		return _settings.getString(R.string.serverAddress);
	}

	@Override
	public int port()
	{
		return Integer.valueOf(_settings.getString(R.string.port));
	}
}
