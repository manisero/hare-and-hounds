package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.core.server.IConnectionManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ConnectionHandler implements IConnectionHandler
{
	private IConnectionManager _server;
	
	@Inject
	public ConnectionHandler(IConnectionManager server)
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
