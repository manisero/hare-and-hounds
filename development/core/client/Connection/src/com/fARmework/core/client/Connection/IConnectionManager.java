package com.fARmework.core.client.Connection;

import com.fARmework.core.data.Message;

public interface IConnectionManager
{
	void connect(IConnectionHandler connectionHandler);
	void send(Message message);
	void disconnect();
}
