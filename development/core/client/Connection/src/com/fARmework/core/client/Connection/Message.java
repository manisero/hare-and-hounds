package com.fARmework.core.client.Connection;

import com.google.gson.Gson;

public class Message 
{
	private String _type;
	private String _data;
	
	public Message(Object data)
	{
		_type = data.getClass().getCanonicalName();
		_data = new Gson().toJson(data);
	}
	
	public String getType()
	{
		return _type;
	}
	
	public String getData()
	{
		return _data;
	}
	
	public <T> T getData(Class<T> dataType)
	{
		return new Gson().fromJson(_data, dataType);
	}
	
	@Override
	public String toString()
	{
		return new Gson().toJson(this);
	}
}
