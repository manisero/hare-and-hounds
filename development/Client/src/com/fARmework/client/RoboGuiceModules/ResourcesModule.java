package com.fARmework.client.RoboGuiceModules;

import com.fARmework.client.ResourcesProvider;
import com.fARmework.client.Logic.IResourcesProvider;
import com.google.inject.AbstractModule;

public class ResourcesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IResourcesProvider.class).to(ResourcesProvider.class);
	}
}
