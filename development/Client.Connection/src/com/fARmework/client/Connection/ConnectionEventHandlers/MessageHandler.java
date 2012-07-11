package com.fARmework.client.Connection.ConnectionEventHandlers;

import com.fARmework.client.Connection.IConnectionHandler;

public class MessageHandler implements IConnectionEventHandler
{
	private String _message;
	
	public MessageHandler(String message)
	{
		_message = message;
	}

	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onMessage(_message);
	}
}
