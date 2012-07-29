package com.fARmework.core.client.Connection;

public interface IConnectionHandler
{
	<T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler);
	void unregisterHandlers();
	
	void onDataReceived(Object data);
}
