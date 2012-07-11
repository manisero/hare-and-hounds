package com.fARmework.client.Connection.ConnectionEvents;

import com.fARmework.client.Connection.IConnectionHandler;

public interface IConnectionEvent
{
	void handle(IConnectionHandler connectionHandler);
}
