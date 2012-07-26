package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.*;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.IDataHandler;

public class ConnectionHandler implements IConnectionHandler
{
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	
	@Override
	public <T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler)
	{
		_dataHandlers.put(dataClass, handler);
	}
	
	@Override
	public void onClientConnected(int clientID)
	{
		System.out.println("Client connected (clientID = " + clientID + ")");
		System.out.println();
	}

	@Override
	public void onClientDisconnected(int clientID)
	{
		System.out.println("Client disconnected (clientID = " + clientID + ")");
		System.out.println();
	}

	@Override
	public void onClientConnectionException(int clientID, Throwable exception)
	{
		System.out.println("Exception (clientID = " + clientID + "): " + exception.getMessage());
		System.out.println();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onDataReceived(int clientID, String dataType, Object data)
	{
		String dataTypeName = dataType.subSequence(dataType.lastIndexOf('.') + 1, dataType.length()).toString();
		
		System.out.println(dataTypeName + " received (clientID = " + clientID + "): " + data.toString());
		System.out.println();
		
		if (!_dataHandlers.containsKey(data.getClass()))
		{
			System.out.println("Could not process " + dataTypeName);
			System.out.println();
			
			return;
		}
		
		_dataHandlers.get(data.getClass()).handle(clientID, data);
	}
}
