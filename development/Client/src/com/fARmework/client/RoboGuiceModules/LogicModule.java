package com.fARmework.client.RoboGuiceModules;

import com.fARmework.client.Logic.IReadTask;
import com.fARmework.client.Logic.ReadTask;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IReadTask.class).to(ReadTask.class);
	}
}
