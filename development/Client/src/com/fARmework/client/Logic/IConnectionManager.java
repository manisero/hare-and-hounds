package com.fARmework.client.Logic;

public interface IConnectionManager
{
	void connect(IMessageHandler<String> messageHandler);
	void disconnect();
}
