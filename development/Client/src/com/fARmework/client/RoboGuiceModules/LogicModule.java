package com.fARmework.client.RoboGuiceModules;

import com.fARmework.client.Logic.IConnectionManager;
import com.fARmework.client.Logic.ISocketCreator;
import com.fARmework.client.Logic.BackgroundTasks.IBackgroundTaskFactory;
import com.fARmework.client.Logic.BackgroundTasks.Impl.BackgroundTaskFactory;
import com.fARmework.client.Logic.Impl.ConnectionManager;
import com.fARmework.client.Logic.Impl.SocketCreator;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IConnectionManager.class).to(ConnectionManager.class);
		bind(IBackgroundTaskFactory.class).to(BackgroundTaskFactory.class);
		bind(ISocketCreator.class).to(SocketCreator.class);
	}
}
