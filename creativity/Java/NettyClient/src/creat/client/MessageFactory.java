package creat.client;

import java.util.*;

public class MessageFactory 
{
	private Map<Class<?>, String> _typeMap = new LinkedHashMap<Class<?>, String>();
	
	private MessageFactory() 
	{ 
		_instance = null;
	}
	
	private static MessageFactory _instance;
	
	public static MessageFactory getInstance()
	{
		if(_instance == null)
		{
			_instance = new MessageFactory();
		}
		
		return _instance;
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
