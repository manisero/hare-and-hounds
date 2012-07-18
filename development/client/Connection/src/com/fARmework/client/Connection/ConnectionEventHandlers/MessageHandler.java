package com.fARmework.client.Connection.ConnectionEventHandlers;

import com.fARmework.client.Connection.IConnectionHandler;
import com.fARmework.client.Connection.Message;
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
