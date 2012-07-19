package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.RockPaperScissors.Client.SettingsProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.Impl.NettyConnectionManager;
import com.fARmework.core.client.Infrastructure.ISettingsProvider;
import com.fARmework.core.data.ISerializationService;
import com.fARmework.core.data.Impl.SerializationService;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// core.Data
		bind(ISerializationService.class).to(SerializationService.class);
		
		// core.Client.Infrastructure
		bind(ISettingsProvider.class).to(SettingsProvider.class);
		
		// core.Client.Connection
		bind(IConnectionManager.class).to(NettyConnectionManager.class);
	}
}
