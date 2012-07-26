package com.fARmework.core.server.Connection;

public interface IConnectionHandler 
{
	void onClientConnected(int clientID);
	void onClientDisconnected(int clientID);
	void onClientConnectionException(int clientID, Throwable exception);
	void onDataReceived(int clientID, String dataType, Object data);
}
