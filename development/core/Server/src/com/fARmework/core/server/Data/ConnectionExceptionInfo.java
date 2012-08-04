package com.fARmework.core.server.Data;

public class ConnectionExceptionInfo
{
	public Throwable Exception;
	
	public ConnectionExceptionInfo(Throwable exception)
	{
		Exception = exception;
	}
}
