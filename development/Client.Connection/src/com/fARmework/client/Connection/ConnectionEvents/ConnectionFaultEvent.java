package com.fARmework.client.Connection.ConnectionEvents;

import com.fARmework.client.Connection.IConnectionHandler;

public class ConnectionFaultEvent implements IConnectionEvent
{
	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onConnectionFault();
	}

}
