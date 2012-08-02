package com.fARmework.core.client.Connection;

public interface IConnectionManager
{
	void connect();
	void disconnect();
	boolean isDisposed();
	
	<T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler);
	void clearDataHandlers();
	
	void send(Object data);
}
