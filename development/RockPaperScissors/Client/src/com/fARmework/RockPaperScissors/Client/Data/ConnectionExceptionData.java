package com.fARmework.RockPaperScissors.Client.Data;

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
