package com.fARmework.core.client.Connection;

public interface IConnectionManager
{
	void connect(IConnectionHandler connectionHandler);
	void send(Object data);
	void disconnect();
}
