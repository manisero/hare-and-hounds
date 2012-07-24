package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.Impl.ConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.Impl.MessageProcessor;
import com.fARmework.core.server.IMessageProcessor;
import com.fARmework.core.server.IConnectionManager;
import com.fARmework.core.server.impl.ConnectionManager;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IConnectionManager.class).to(ConnectionManager.class);
		bind(IMessageProcessor.class).to(MessageProcessor.class);
		bind(IConnectionHandler.class).to(ConnectionHandler.class);
	}
}
