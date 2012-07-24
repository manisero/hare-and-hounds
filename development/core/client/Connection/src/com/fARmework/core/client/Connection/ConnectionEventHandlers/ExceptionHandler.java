package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;

public class ExceptionHandler implements IConnectionEventHandler
{
	private Throwable _exception;

	public ExceptionHandler(Throwable exception)
	{
		_exception = exception;
	}
	
	@Override
	public void handleWith(IConnectionHandler connectionHandler)
	{
		connectionHandler.onException(_exception);
	}
}
