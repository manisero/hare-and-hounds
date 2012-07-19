package com.fARmework.core.data.Impl;

import com.fARmework.core.data.ISerializationService;
import com.fARmework.core.data.Message;
import com.google.gson.Gson;

public class SerializationService implements ISerializationService
{
	@Override
	public String serialize(Object data)
	{
		return new Gson().toJson(data);
	}

	@Override
	public Message deserialize(String message)
	{
		return new Gson().fromJson(message, Message.class);
	}

	@Override
	public <T> T deserialize(String data, Class<T> dataType)
	{
		return new Gson().fromJson(data, dataType);
	}
}
