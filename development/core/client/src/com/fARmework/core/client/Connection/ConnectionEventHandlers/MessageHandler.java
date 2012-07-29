package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;

public class MessageHandler implements IConnectionEventHandler
{
	private Object _data;
	
	public MessageHandler(Object data)
	{
		_data = data;
	}

	@Override
	public void handleWith(IConnectionHandler connectionHandler)
	{
		connectionHandler.onDataReceived(_data);
	}
}
