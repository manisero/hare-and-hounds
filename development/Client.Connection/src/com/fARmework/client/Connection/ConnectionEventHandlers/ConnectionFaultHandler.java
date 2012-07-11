package com.fARmework.client.Connection.ConnectionEventHandlers;

import com.fARmework.client.Connection.IConnectionHandler;

public class ConnectionFaultHandler implements IConnectionEventHandler
{
	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onConnectionFault();
	}

}
