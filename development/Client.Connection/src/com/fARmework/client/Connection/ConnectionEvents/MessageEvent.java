package com.fARmework.client.Connection.ConnectionEvents;

import com.fARmework.client.Connection.IConnectionHandler;

public class MessageEvent implements IConnectionEvent
{
	private String _message;
	
	public MessageEvent(String message)
	{
		_message = message;
	}

	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onMessage(_message);
	}
}
