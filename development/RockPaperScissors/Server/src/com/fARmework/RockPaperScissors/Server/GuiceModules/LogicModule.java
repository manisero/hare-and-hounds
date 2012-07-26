package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.Impl.ConnectionHandler;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IConnectionHandler.class).to(ConnectionHandler.class).asEagerSingleton();
	}
}
