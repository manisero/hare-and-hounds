package com.fARmework.RockPaperScissors.Client.Logic;


public interface IConnectionHandler extends com.fARmework.core.client.Connection.IConnectionHandler
{
	<T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler);
	void unregisterHandlers();
}
