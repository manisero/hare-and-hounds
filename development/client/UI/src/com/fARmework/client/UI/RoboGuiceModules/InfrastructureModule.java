package com.fARmework.client.UI.RoboGuiceModules;

import com.fARmework.client.Infrastructure.ISettingsProvider;
import com.fARmework.client.UI.SettingsProvider;
import com.google.inject.AbstractModule;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(SettingsProvider.class);
	}
}
