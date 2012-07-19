package com.fARmework.core.data;

public interface IDataService
{
	String serialize(Object data);
	<T> T deserialize(String data, Class<T> dataType);
	
	Message toMessage(Object data);
	String toSerializedMessage(Object data);
	Message deserializeMessage(String message);
}
