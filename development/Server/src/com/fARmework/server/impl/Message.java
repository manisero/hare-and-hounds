package com.fARmework.server.impl;

public class Message 
{
	private String _type;
	
	private Object _object;
	
	public Message(String type, Object object)
	{
		_type = type;
		_object = object;
	}
	
	public String getType()
	{
		return _type;
	}
	
	public Object getObject()
	{
		return _object;
	}
}
