package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.Infrastructure._impl.*;
import com.google.inject.AbstractModule;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(SettingsProvider.class).asEagerSingleton();
	}
}
