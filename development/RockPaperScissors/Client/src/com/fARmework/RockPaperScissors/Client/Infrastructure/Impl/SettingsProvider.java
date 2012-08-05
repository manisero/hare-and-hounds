package com.fARmework.RockPaperScissors.Client.Infrastructure.Impl;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.google.inject.Singleton;

@Singleton
public class SettingsProvider implements ISettingsProvider
{
	@Override
	public String serverAddress()
	{
		return ResourcesProvider.getString(R.string.defaultServerAddress);
	}

	@Override
	public int port()
	{
		return ResourcesProvider.getInteger(R.integer.port);
	}

	@Override
	public String userName()
	{
		return ResourcesProvider.getString(R.string.defaultUserName);
	}
}
