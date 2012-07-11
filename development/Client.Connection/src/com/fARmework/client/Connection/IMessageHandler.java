package com.fARmework.client.Connection;

public interface IMessageHandler<T>
{
	void onMessage(T value);
}
