package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.RockPaperScissors.Client.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Client.Logic.Impl.ConnectionHandler;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IConnectionHandler.class).to(ConnectionHandler.class);
	}
}
