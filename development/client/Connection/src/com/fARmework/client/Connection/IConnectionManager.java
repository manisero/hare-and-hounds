package com.fARmework.client.Connection;

public interface IConnectionManager
{
	void connect(IConnectionHandler connectionHandler);
	void send(Message message);
	void disconnect();
}
