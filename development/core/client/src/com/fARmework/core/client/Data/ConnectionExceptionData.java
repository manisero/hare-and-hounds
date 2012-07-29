package com.fARmework.core.client.Data;

public class ConnectionExceptionData
{
	private Throwable _exception;
	
	public ConnectionExceptionData(Throwable exception)
	{
		_exception = exception;
	}
	
	public Throwable getException()
	{
		return _exception;
	}
}
