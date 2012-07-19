package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.core.server.IServer;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ConnectionHandler implements IConnectionHandler
{
	private IServer _server;
	
	@Inject
	public ConnectionHandler(IServer server)
	{
		_server = server;
	}
	
	@Override
	public void connect()
	{
		_server.start();
	}

	@Override
	public void send(String message)
	{
		_server.send(message);
	}
}
