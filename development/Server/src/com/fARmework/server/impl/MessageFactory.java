package com.fARmework.server.impl;

import com.google.inject.*;
import java.util.*;

@Singleton
public class MessageFactory 
{
	private Map<Class<?>, String> _typeMap = new LinkedHashMap<Class<?>, String>();
	
	public MessageFactory()
	{
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
			return new Message(_typeMap.get(object.getClass()), object);
		}
		
		else
		{
			throw new UnregisteredObjectTypeException(object.toString());
		}
	}
}
