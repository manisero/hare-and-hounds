package com.fARmework.client.Connection;

public interface IConnectionManager
{
	void connect(IConnectionHandler connectionHandler);
	void disconnect();
}
