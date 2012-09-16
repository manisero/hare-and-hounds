package com.fARmework.core.data;

public class Message
{
	private String _type;
	private String _data;
	
	public Message(String type, String data)
	{
		_type = type;
		_data = data;
	}
	
	public String getType()
	{
		return _type;
	}
	
	public String getData()
	{
		return _data;
	}
}
