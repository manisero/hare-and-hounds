package com.fARmework.HareAndHounds.Server.GuiceModules;

import com.fARmework.core.data.*;
import com.fARmework.core.data._impl.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.core.server.Connection._impl.*;
import com.fARmework.core.server.Infrastructure.*;
import com.google.inject.*;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// core.Data
		bind(IDataRegistry.class).to(DataRegistry.class).asEagerSingleton();
		bind(IDataService.class).to(DataService.class);
		
		// core.Server.Infrastructure
		bind(ISettingsProvider.class).to(com.fARmework.HareAndHounds.Server.Infrastructure.ISettingsProvider.class);
		
		// core.Server.Connection
		bind(IConnectionManager.class).to(ConnectionManager.class).asEagerSingleton();
	}
}
