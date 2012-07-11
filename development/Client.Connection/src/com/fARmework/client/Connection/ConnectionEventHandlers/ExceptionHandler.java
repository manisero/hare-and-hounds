package com.fARmework.client.Connection.ConnectionEventHandlers;

import com.fARmework.client.Connection.IConnectionHandler;

public class ExceptionHandler implements IConnectionEventHandler
{
	private Throwable _exception;

	public ExceptionHandler(Throwable exception)
	{
		_exception = exception;
	}
	
	@Override
	public void handle(IConnectionHandler connectionHandler)
	{
		connectionHandler.onException(_exception);
	}
}
