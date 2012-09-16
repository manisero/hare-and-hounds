package com.fARmework.HareAndHounds.Server;

import com.fARmework.core.server.Infrastructure.ISettingsProvider;
import com.google.inject.Singleton;

@Singleton
public class SettingsProvider implements ISettingsProvider
{
	@Override
	public int getPort()
	{
		return 6969;
	}
}
