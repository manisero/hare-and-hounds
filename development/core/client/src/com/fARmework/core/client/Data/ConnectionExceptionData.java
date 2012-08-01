package com.fARmework.core.client.Data;

public class ConnectionExceptionData
{
	public Throwable Exception;
	
	public ConnectionExceptionData(Throwable exception)
	{
		Exception = exception;
	}
}
