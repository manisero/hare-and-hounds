package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;

public class ConnectionSuccessHandler implements IConnectionEventHandler
{
	@Override
	public void handleWith(IConnectionHandler connectionHandler)
	{
		connectionHandler.onConnectionSuccess();
	}
}
