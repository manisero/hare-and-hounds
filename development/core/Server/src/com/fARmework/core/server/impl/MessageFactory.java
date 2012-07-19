package com.fARmework.core.server.impl;

import com.fARmework.core.data.ISerializationService;
import com.fARmework.core.data.Message;
import com.google.inject.*;
import java.util.*;

@Singleton
public class MessageFactory 
{
	private ISerializationService _serializationService;
	
	private Map<Class<?>, String> _typeMap = new LinkedHashMap<Class<?>, String>();
	
	@Inject
	public MessageFactory(ISerializationService serializationService)
	{
		_serializationService = serializationService;
		
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
			String dataType = object.getClass().getCanonicalName();
			String data = _serializationService.serialize(object);
			return new Message(dataType, data);
		}
		
		else
		{
			throw new UnregisteredObjectTypeException(object.toString());
		}
	}
}
