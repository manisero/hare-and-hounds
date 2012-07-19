package com.fARmework.core.data;

public interface ISerializationService
{
	Message deserializeMessage(String message);
	
	String serialize(Object data);
	<T> T deserialize(String data, Class<T> dataType);
}
