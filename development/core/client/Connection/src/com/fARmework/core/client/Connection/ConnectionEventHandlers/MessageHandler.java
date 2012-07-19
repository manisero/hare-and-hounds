package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;
import com.fARmework.core.client.Connection.Message;
import com.google.gson.Gson;

public class MessageHandler implements IConnectionEventHandler
{
	private String _message;
	
	public MessageHandler(String message)
	{
		_message = message;
	}

	@Override
	public void handleWith(IConnectionHandler connectionHandler)
	{
		Message message = new Gson().fromJson(_message, Message.class);
		connectionHandler.onMessage(message.getType() + ": " + message.getData().toString());
	}
}
