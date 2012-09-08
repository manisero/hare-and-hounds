package com.fARmework.HareAndHounds.Server.GuiceModules;

import com.fARmework.HareAndHounds.Server.SettingsProvider;
import com.fARmework.core.data.*;
import com.fARmework.core.data._impl.*;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.core.server.Connection._impl.ConnectionManager;
import com.fARmework.core.server.Infrastructure.ISettingsProvider;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// core.Data
		bind(IDataRegistry.class).to(DataRegistry.class).asEagerSingleton();
		bind(IDataService.class).to(DataService.class);
		
		// core.Server.Infrastructure
		bind(ISettingsProvider.class).to(SettingsProvider.class);
		
		// core.Server.Connection
		bind(IConnectionManager.class).to(ConnectionManager.class).asEagerSingleton();
	}
}
