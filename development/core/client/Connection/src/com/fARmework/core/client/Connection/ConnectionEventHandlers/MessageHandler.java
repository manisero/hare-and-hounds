package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;
import com.fARmework.core.data.Message;

public class MessageHandler implements IConnectionEventHandler
{
	private Message _message;
	
	public MessageHandler(Message message)
	{
		_message = message;
	}

	@Override
	public void handleWith(IConnectionHandler connectionHandler)
	{
		connectionHandler.onMessage(_message);
	}
}
