package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;

public class MessageHandler implements IConnectionEventHandler
{
	private String _dataType;
	private Object _data;
	
	public MessageHandler(String dataType, Object data)
	{
		_dataType = dataType;
		_data = data;
	}

	@Override
	public void handleWith(IConnectionHandler connectionHandler)
	{
		connectionHandler.onDataReceived(_dataType, _data);
	}
}
