package com.fARmework.core.server;

public interface IConnectionHandler 
{
	void onClientConnected(int clientID);
	void onClientDisconnected(int clientID);
	void onClientConnectionException(int clientID, Throwable exception);
	void onMessageReceived(int clientID, String dataType, Object data);
}
