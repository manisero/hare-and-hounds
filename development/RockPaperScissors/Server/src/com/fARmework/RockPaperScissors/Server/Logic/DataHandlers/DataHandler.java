package com.fARmework.RockPaperScissors.Server.Logic.DataHandlers;

import com.fARmework.core.server.Connection.IDataHandler;

public abstract class DataHandler<T> implements IDataHandler<T>
{
	@Override
	public void handle(int clientID, T data)
	{
		System.out.println(data.getClass().getSimpleName() + " received (clientID = " + clientID + ")");
		handleData(clientID, data);
	}
	
	protected abstract void handleData(int clientID, T data);
}
