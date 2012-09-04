package com.fARmework.core.client.Connection;

public interface IConnectionManager
{
	void connect();
	void connect(String serverAddress);
	void disconnect();
	
	<T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler);
	void unregisterDataHandlers();
	<T> void unregisterDataHandlers(Class<T> dataClass);
	
	void send(Object data);
}
