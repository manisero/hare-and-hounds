package com.fARmework.client.Connection;

public interface IConnectionManager
{
	void connect(IConnectionHandler connectionHandler);
	void send(String message);
	void disconnect();
}
