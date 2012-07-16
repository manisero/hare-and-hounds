package com.fARmework.RockPaperScissors.Server;

import com.fARmework.RockPaperScissors.Server.Logic.ConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.MessageProcessor;
import com.fARmework.server.IMessageProcessor;
import com.fARmework.server.IServer;
import com.fARmework.server.impl.Server;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IServer.class).to(Server.class);
		bind(IMessageProcessor.class).to(MessageProcessor.class);
		bind(IConnectionHandler.class).to(ConnectionHandler.class);
	}
}
