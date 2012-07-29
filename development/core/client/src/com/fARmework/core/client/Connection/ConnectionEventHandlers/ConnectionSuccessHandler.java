package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;
import com.fARmework.core.client.Data.ConnectionSuccessData;

public class ConnectionSuccessHandler implements IConnectionEventHandler
{
	@Override
	public void handleWith(IConnectionHandler connectionHandler)
	{
		connectionHandler.onDataReceived(new ConnectionSuccessData());
	}
}
