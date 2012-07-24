package com.fARmework.RockPaperScissors.Server;

import com.fARmework.core.server.Infrastructure.ISettingsProvider;

public class SettingsProvider implements ISettingsProvider
{
	@Override
	public int getPort()
	{
		return 6969;
	}
}
