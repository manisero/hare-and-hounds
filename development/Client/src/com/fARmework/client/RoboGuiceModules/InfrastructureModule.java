package com.fARmework.client.RoboGuiceModules;

import com.fARmework.client.SettingsProvider;
import com.fARmework.client.Infrastructure.ISettingsProvider;
import com.google.inject.AbstractModule;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(SettingsProvider.class);
	}
}
