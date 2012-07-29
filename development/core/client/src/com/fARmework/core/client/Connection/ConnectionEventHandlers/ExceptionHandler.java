package com.fARmework.core.client.Connection.ConnectionEventHandlers;

import com.fARmework.core.client.Connection.IConnectionHandler;
import com.fARmework.core.client.Data.ConnectionExceptionData;

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
		connectionHandler.onDataReceived(new ConnectionExceptionData(_exception));
	}
}
