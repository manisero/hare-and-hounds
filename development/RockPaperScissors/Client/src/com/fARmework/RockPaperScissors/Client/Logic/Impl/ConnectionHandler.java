package com.fARmework.RockPaperScissors.Client.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Client.Data.ConnectionExceptionData;
import com.fARmework.RockPaperScissors.Client.Data.ConnectionFaultData;
import com.fARmework.RockPaperScissors.Client.Data.ConnectionSuccessData;
import com.fARmework.RockPaperScissors.Client.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Data.IDataHandler;

@SuppressWarnings("unchecked")
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
	public void unregisterHandlers()
	{
		_dataHandlers.clear();
	}
	
	@Override
	public void onConnectionSuccess()
	{
		handle(new ConnectionSuccessData());
	}

	@Override
	public void onConnectionFault()
	{
		handle(new ConnectionFaultData());
	}

	@Override
	public void onDataReceived(String dataType, Object data)
	{
		handle(data);
	}

	@Override
	public void onException(Throwable exception)
	{
		handle(new ConnectionExceptionData(exception));
	}
	
	private void handle(Object data)
	{
		if (_dataHandlers.containsKey(data.getClass()))
		{
			_dataHandlers.get(data.getClass()).handle(data);
		}
	}
}
