package com.fARmework.client.Connection.ConnectionEvents;

import com.fARmework.client.Connection.IConnectionHandler;

public class ConnectionSuccessEvent implements IConnectionEvent
{
	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onConnectionSuccess();
	}
}
