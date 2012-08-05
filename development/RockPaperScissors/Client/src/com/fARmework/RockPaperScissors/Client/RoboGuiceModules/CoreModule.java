package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.Impl.NettyConnectionManagerProvider;
import com.fARmework.core.client.Infrastructure.ISettingsProvider;
import com.fARmework.core.data.IDataRegistry;
import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Impl.DataRegistry;
import com.fARmework.core.data.Impl.DataService;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// core.Data
		bind(IDataRegistry.class).to(DataRegistry.class).asEagerSingleton();
		bind(IDataService.class).to(DataService.class);
		
		// core.Client.Infrastructure
		bind(ISettingsProvider.class).to(com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider.class);
		
		// core.Client.Connection
		bind(IConnectionManager.class).toProvider(NettyConnectionManagerProvider.class);
	}
}
