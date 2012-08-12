package com.fARmework.core.server.Connection;

public interface IDataHandler<T>
{
	void handle(int clientID, T data);
}
