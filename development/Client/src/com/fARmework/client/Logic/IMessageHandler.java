package com.fARmework.client.Logic;

public interface IMessageHandler<T>
{
	void onMessage(T value);
}
