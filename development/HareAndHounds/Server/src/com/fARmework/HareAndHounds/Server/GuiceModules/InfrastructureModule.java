package com.fARmework.HareAndHounds.Server.GuiceModules;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Infrastructure._impl.*;
import com.google.inject.*;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(SettingsProvider.class).asEagerSingleton();
		bind(ISimulatorDataProvider.class).to(SimulatorDataProvider.class).asEagerSingleton();
	}
}
