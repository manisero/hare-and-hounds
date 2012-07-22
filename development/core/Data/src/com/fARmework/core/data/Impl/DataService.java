package com.fARmework.core.data.Impl;

import com.fARmework.core.data.IDataRegistry;
import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.google.gson.Gson;
import com.google.inject.Inject;

public class DataService implements IDataService
{
	private IDataRegistry _dataRegistry;
	
	@Inject
	public DataService(IDataRegistry dataRegistry)
	{
		_dataRegistry = dataRegistry;
	}
	
	@Override
	public String serialize(Object data)
	{
		return new Gson().toJson(data);
	}

	@Override
	public <T> T deserialize(String data, Class<T> dataClass)
	{
		return new Gson().fromJson(data, dataClass);
	}
	
	@Override
	public Message deserializeMessage(String message)
	{
		return deserialize(message, Message.class);
	}
	
	@Override
	public Message toMessage(Object data)
	{
		return new Message(_dataRegistry.getDataType(data.getClass()), serialize(data));
	}
	
	@Override
	public String toSerializedMessage(Object data)
	{
		return serialize(toMessage(data));
	}

	@Override
	public Object fromMessage(Message message)
	{
		return deserialize(message.getData(), _dataRegistry.getDataClass(message.getType()));
	}
}
