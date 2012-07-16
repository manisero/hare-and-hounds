package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.client.Connection.IConnectionManager;
import com.fARmework.client.Connection.Impl.NettyConnectionManager;
import com.google.inject.AbstractModule;

public class ConnectionModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IConnectionManager.class).to(NettyConnectionManager.class);
	}
}
