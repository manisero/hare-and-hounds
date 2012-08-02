package com.fARmework.RockPaperScissors.Server.Logic.DataHandlers;

import com.fARmework.core.server.Connection.IDataHandler;

public abstract class DataHandler<T> implements IDataHandler<T>
{
	@Override
	public void handle(int clientID, T data)
	{
		String dataClassName = data.getClass().getCanonicalName();
		String dataTypeName = dataClassName.subSequence(dataClassName.lastIndexOf('.') + 1, dataClassName.length()).toString();
		
		System.out.println(dataTypeName + " received (clientID = " + clientID + ")");
	}
}
