package com.fARmework.RockPaperScissors.Server.Logic.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.core.data.IDataRegistry;
import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Message;
import com.fARmework.core.server.IMessageProcessor;
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
	private IDataService _dataService;
	private IDataRegistry _dataRegistry;
	
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	
	@Inject
	public MessageProcessor(IConnectionHandler connectionHandler, IDataService dataService, IDataRegistry dataRegistry)
	{
		_connectionHandler = connectionHandler;
		_dataService = dataService;
		_dataRegistry = dataRegistry;
	}
	
	public <T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler)
	{
		_dataHandlers.put(dataClass, handler);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void process(Message message)
	{
		String dataType = message.getType();
		String dataTypeName = dataType.subSequence(dataType.lastIndexOf('.') + 1, dataType.length()).toString(); 
		
		System.out.println(dataTypeName + ": " + message.getData());
		System.out.println("");
		
		if (!_dataRegistry.isRegistered(dataType))
		{
			System.out.println("Unknown data received: " + dataTypeName);
			_connectionHandler.send("Unknown data received: " + dataTypeName);
			
			return;
		}
		
		Object data = _dataService.fromMessage(message);
		
		if (!_dataHandlers.containsKey(data.getClass()))
		{
			System.out.println("Could not process data: " + dataTypeName);
			_connectionHandler.send("Could not process data: " + dataTypeName);
			
			return;
		}
		
		 _dataHandlers.get(data.getClass()).handle(data);
		
		_connectionHandler.send(dataTypeName + " processed successfully");
	}
}
