package com.fARmework.core.data;

public interface ISerializationService
{
	String serialize(Object data);
	Message deserialize(String message);
	<T> T deserialize(String data, Class<T> dataType);
}
