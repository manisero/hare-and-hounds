package com.fARmework.client.Connection.ConnectionEventHandlers;

import com.fARmework.client.Connection.IConnectionHandler;

public class ConnectionSuccessHandler implements IConnectionEventHandler
{
	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onConnectionSuccess();
	}
}
