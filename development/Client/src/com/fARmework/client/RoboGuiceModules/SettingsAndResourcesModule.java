package com.fARmework.client.RoboGuiceModules;

import com.fARmework.client.ResourcesProvider;
import com.fARmework.client.SettingsProvider;
import com.fARmework.client.Logic.IResourcesProvider;
import com.fARmework.client.Logic.ISettingsProvider;
import com.google.inject.AbstractModule;

public class SettingsAndResourcesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(SettingsProvider.class);
		bind(IResourcesProvider.class).to(ResourcesProvider.class);
	}
}
