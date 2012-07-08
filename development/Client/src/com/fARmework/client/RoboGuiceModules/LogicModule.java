package com.fARmework.client.RoboGuiceModules;

import com.fARmework.client.Logic.IConnectionManager;
import com.fARmework.client.Logic.Impl.NettyConnectionManager;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IConnectionManager.class).to(NettyConnectionManager.class);
	}
}
