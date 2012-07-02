package com.fARmework.server;

public class UnregisteredObjectTypeException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public UnregisteredObjectTypeException(String type)
	{
		super("Unregistered object type: " + type);
	}
}