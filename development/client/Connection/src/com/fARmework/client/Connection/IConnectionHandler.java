package com.fARmework.client.Connection;

public interface IConnectionHandler
{
	void onConnectionSuccess();
	void onConnectionFault();
	void onException(Throwable exception);
	void onMessage(String message);
}
