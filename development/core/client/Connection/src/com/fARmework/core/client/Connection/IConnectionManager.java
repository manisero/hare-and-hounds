package com.fARmework.core.client.Connection;

public interface IConnectionManager
{
	void connect();
	void send(Object data);
	void disconnect();
}
