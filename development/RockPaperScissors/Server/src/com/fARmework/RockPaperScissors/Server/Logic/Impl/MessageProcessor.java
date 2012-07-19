package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.core.server.IMessageProcessor;
import com.fARmework.core.server.impl.Message;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MessageProcessor implements IMessageProcessor
{
	public interface IDataHandler<T>
	{
		void handle(T data);
	}
	
	private IConnectionHandler _connectionHandler;
	
	private Map<String, Class<?>> _dataMappings = new LinkedHashMap<String, Class<?>>();
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	
	@Inject
	public MessageProcessor(IConnectionHandler connectionHandler)
	{
		_connectionHandler = connectionHandler;
	}
	
	public <T> void registerHandler(Class<T> dataType, IDataHandler<T> handler)
	{
		_dataMappings.put(dataType.getCanonicalName(), dataType);
		_dataHandlers.put(dataType, handler);
	}
	
	private boolean isRegistered(String dataType)
	{
		return _dataMappings.containsKey(dataType) && _dataHandlers.containsKey(_dataMappings.get(dataType));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void process(Message message)
	{
		String dataType = message.getType();
		String dataTypeName = dataType.subSequence(dataType.lastIndexOf('.') + 1, dataType.length()).toString(); 
		
		if (false == isRegistered(dataType))
		{
			System.out.println("Unknown data received: " + dataTypeName);
			_connectionHandler.send("Unknown data received: " + dataTypeName);
		}
		
		System.out.println(dataTypeName + ": " + message.getData());
		System.out.println("");
		
		Object data = message.getData(_dataMappings.get(dataType));
		 _dataHandlers.get(data.getClass()).handle(data);
		
		_connectionHandler.send(dataTypeName + " processed successfully");
	}
}
