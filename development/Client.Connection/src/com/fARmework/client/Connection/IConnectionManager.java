package com.fARmework.client.Connection;

public interface IConnectionManager
{
	void connect(IMessageHandler<String> messageHandler);
	void disconnect();
}
