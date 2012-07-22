package com.fARmework.core.server.impl;

import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.google.inject.*;
import java.util.*;

@Singleton
public class MessageFactory 
{
	private IDataService _dataService;
	
	private Map<Class<?>, String> _typeMap = new LinkedHashMap<Class<?>, String>();
	
	@Inject
	public MessageFactory(IDataService serializationService)
	{
		_dataService = serializationService;
		
		registerTypes();
	}
	
	private void registerTypes()
	{
		register(String.class, "STRING_MESSAGE");
	}
	
	public void register(Class<?> objectClass, String type)
	{
		_typeMap.put(objectClass, type);
	}
	
	public Message getMessage(Object object) throws UnregisteredObjectTypeException
	{
		if(_typeMap.containsKey(object.getClass()))
		{
			return _dataService.toMessage(object);
		}
		
		else
		{
			throw new UnregisteredObjectTypeException(object.toString());
		}
	}
}
