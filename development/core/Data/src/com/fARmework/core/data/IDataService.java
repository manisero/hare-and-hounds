package com.fARmework.core.data;

public interface IDataService
{
	String serialize(Object data);
	
	<T> T deserialize(String data, Class<T> dataClass);
	Message deserializeMessage(String message);
	
	Message toMessage(Object data);
	String toSerializedMessage(Object data);
	
	Object fromMessage(Message message);
}
