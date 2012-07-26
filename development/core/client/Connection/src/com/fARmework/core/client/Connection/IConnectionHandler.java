package com.fARmework.core.client.Connection;

public interface IConnectionHandler
{
	void onConnectionSuccess();
	void onConnectionFault();
	void onDataReceived(String dataType, Object data);
	void onException(Throwable exception);
}
