package com.fARmework.core.data.Impl;

import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.google.gson.Gson;

public class DataService implements IDataService
{
	@Override
	public String serialize(Object data)
	{
		return new Gson().toJson(data);
	}

	@Override
	public <T> T deserialize(String data, Class<T> dataType)
	{
		return new Gson().fromJson(data, dataType);
	}
	
	@Override
	public Message toMessage(Object data)
	{
		return new Message(data.getClass().getCanonicalName(), serialize(data));
	}
	
	@Override
	public String toSerializedMessage(Object data)
	{
		return serialize(toMessage(data));
	}
	
	@Override
	public Message deserializeMessage(String message)
	{
		return deserialize(message, Message.class);
	}
}
