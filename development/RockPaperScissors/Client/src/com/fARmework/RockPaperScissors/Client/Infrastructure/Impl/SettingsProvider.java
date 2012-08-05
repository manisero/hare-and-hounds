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
		return ResourcesProvider.get(R.string.defaultServerAddress);
	}

	@Override
	public int port()
	{
		return Integer.valueOf(ResourcesProvider.get(R.string.port));
	}

	@Override
	public String userName()
	{
		return ResourcesProvider.get(R.string.defaultUserName);
	}
}
