package com.fARmework.core.client.Connection;

import com.fARmework.core.data.Message;

public interface IConnectionHandler
{
	void onConnectionSuccess();
	void onConnectionFault();
	void onMessage(Message message);
	void onException(Throwable exception);
}
