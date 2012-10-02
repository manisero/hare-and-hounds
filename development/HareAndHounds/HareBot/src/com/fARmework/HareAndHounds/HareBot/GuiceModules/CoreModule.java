package com.fARmework.HareAndHounds.HareBot.GuiceModules;

import com.fARmework.HareAndHounds.HareBot.Connection._impl.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Infrastructure.*;
import com.fARmework.core.data.*;
import com.fARmework.core.data._impl.*;
import com.google.inject.*;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// core.Data
		bind(IDataRegistry.class).to(DataRegistry.class).asEagerSingleton();
		bind(IDataService.class).to(DataService.class);
		
		// core.Client.Infrastructure
		bind(ISettingsProvider.class).to(com.fARmework.HareAndHounds.HareBot.Infrastructure.ISettingsProvider.class);
		
		// core.Client.Connection
		bind(IConnectionManager.class).to(ConnectionManager.class).asEagerSingleton();
	}
}
