package com.fARmework.core.client.Connection.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.core.client.Connection.IConnectionHandler;
import com.fARmework.core.client.Connection.IDataHandler;

@SuppressWarnings("unchecked")
public class ConnectionHandler implements IConnectionHandler
{
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	
	@Override
	public <T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler)
	{
		_dataHandlers.put(dataClass, handler);
	}
	
	@Override
	public void unregisterHandlers()
	{
		_dataHandlers.clear();
	}

	@Override
	public void onDataReceived(Object data)
	{
		handle(data);
	}
	
	private void handle(Object data)
	{
		if (_dataHandlers.containsKey(data.getClass()))
		{
			_dataHandlers.get(data.getClass()).handle(data);
		}
	}
}
