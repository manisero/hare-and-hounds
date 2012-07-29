package com.fARmework.core.client.Connection;

public interface IConnectionHandler
{
	<T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler);
	void unregisterHandlers();
	
	void onConnectionSuccess();
	void onConnectionFault();
	void onDataReceived(String dataType, Object data);
	void onException(Throwable exception);
}
