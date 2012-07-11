package com.fARmework.client.Connection.ConnectionEvents;

import com.fARmework.client.Connection.IConnectionHandler;

public class ExceptionEvent implements IConnectionEvent
{
	private Throwable _exception;

	public ExceptionEvent(Throwable exception)
	{
		_exception = exception;
	}
	
	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onException(_exception);
	}
}
