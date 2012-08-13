package com.fARmework.core.server.Connection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class DataHandlersCollection
{
	private Map<Class<?>, IDataHandler> _commonDataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	private Map<Integer, Map<Class<?>, IDataHandler>> _individualDataHandlers = new LinkedHashMap<Integer, Map<Class<?>, IDataHandler>>();
	
	// register methods
	
	public <T> void register(Class<T> dataClass, IDataHandler<T> handler)
	{
		_commonDataHandlers.put(dataClass, handler);
	}
	
	public <T> void register(Class<T> dataClass, int clientID, IDataHandler<T> handler)
	{
		if (!_individualDataHandlers.containsKey(clientID))
		{
			_individualDataHandlers.put(clientID, new LinkedHashMap<Class<?>, IDataHandler>());
		}
		
		_individualDataHandlers.get(clientID).put(dataClass, handler);
	}
	
	public <T> void register(Class<T> dataClass, List<Integer> clientIDs, IDataHandler<T> handler)
	{
		for (Integer clientID : clientIDs)
		{
			register(dataClass, clientID, handler);
		}
	}
	
	// isRegistered methods
	
	public <T> boolean isRegistered(Class<T> dataClass)
	{
		return _commonDataHandlers.containsKey(dataClass);
	}
	
	public <T> boolean isRegistered(Class<T> dataClass, int clientID)
	{
		return _individualDataHandlers.containsKey(clientID) && _individualDataHandlers.get(clientID).containsKey(dataClass);
	}
	
	// get methods
	
	@SuppressWarnings("unchecked")
	public <T> IDataHandler<T> get(Class<T> dataClass)
	{
		return isRegistered(dataClass) ? _commonDataHandlers.get(dataClass) : null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> IDataHandler<T> get(Class<T> dataClass, int clientID)
	{
		return isRegistered(dataClass, clientID) ? _individualDataHandlers.get(clientID).get(dataClass) : get(dataClass);
	}
	
	// clear methods
	
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
