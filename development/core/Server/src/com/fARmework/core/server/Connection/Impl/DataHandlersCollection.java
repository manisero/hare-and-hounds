package com.fARmework.core.server.Connection.Impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fARmework.core.server.Connection.IDataHandler;

@SuppressWarnings("rawtypes")
public class DataHandlersCollection
{
	private Map<Class<?>, IDataHandler> _commonDataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	private Map<Integer, Map<Class<?>, IDataHandler>> _individualDataHandlers = new LinkedHashMap<Integer, Map<Class<?>, IDataHandler>>();
	
	public <T> void register(Class<T> dataClass, IDataHandler<T> handler)
	{
		_commonDataHandlers.put(dataClass, handler);
	}
	
	public <T> void register(Class<T> dataClass, IDataHandler<T> handler, int clientID)
	{
		if (!_individualDataHandlers.containsKey(clientID))
		{
			_individualDataHandlers.put(clientID, new LinkedHashMap<Class<?>, IDataHandler>());
		}
		
		_individualDataHandlers.get(clientID).put(dataClass, handler);
	}
	
	public <T> void register(Class<T> dataClass, IDataHandler<T> handler, List<Integer> clientIDs)
	{
		for (Integer clientID : clientIDs)
		{
			register(dataClass, handler, clientID);
		}
	}
	
	public void clear()
	{
		_commonDataHandlers.clear();
		_individualDataHandlers.clear();
	}
	
	public void clear(int clientID)
	{
		_individualDataHandlers.remove(clientID);
	}
	
	public void clear(List<Integer> clientIDs)
	{
		for (Integer clientID : clientIDs)
		{
			clear(clientID);
		}
	}
	
	public <T> void clear(Class<T> dataClass)
	{
		_commonDataHandlers.remove(dataClass);
		
		for (Integer clientID : _individualDataHandlers.keySet())
		{
			clear(dataClass, clientID);
		}
	}
	
	public <T> void clear(Class<T> dataClass, int clientID)
	{
		if (_individualDataHandlers.containsKey(clientID))
		{
			Map<Class<?>, IDataHandler> individualMap = _individualDataHandlers.get(clientID);
			individualMap.remove(dataClass);
			
			if (individualMap.size() == 0)
				_individualDataHandlers.remove(clientID);
		}
	}
	
	public <T> void clear(Class<T> dataClass, List<Integer> clientIDs)
	{
		for (Integer clientID : clientIDs)
		{
			clear(dataClass, clientID);
		}
	}
}
