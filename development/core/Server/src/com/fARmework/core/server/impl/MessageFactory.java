package com.fARmework.core.server.impl;

import com.fARmework.core.data.Message;
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
			return new Message(object);
		}
		
		else
		{
			throw new UnregisteredObjectTypeException(object.toString());
		}
	}
}
