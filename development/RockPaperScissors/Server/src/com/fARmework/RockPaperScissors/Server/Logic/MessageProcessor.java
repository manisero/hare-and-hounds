package com.fARmework.RockPaperScissors.Server.Logic;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fARmework.modules.ScreenGestures.Data.GestureData;
import com.fARmework.server.IMessageProcessor;
import com.fARmework.server.impl.Message;
import com.google.inject.Inject;

public class MessageProcessor implements IMessageProcessor
{
	private IConnectionHandler _connectionHandler;
	
	private Map<String, Class<?>> _mappings = new LinkedHashMap<String, Class<?>>();
	
	@Inject
	public MessageProcessor(IConnectionHandler connectionHandler)
	{
		_connectionHandler = connectionHandler;
		
		registerMapping(GestureData.class);
	}
	
	private void registerMapping(Class<?> dataType)
	{
		_mappings.put(dataType.getCanonicalName(), dataType);
	}
	
	@Override
	public void process(Message message)
	{
		String dataType = message.getType();
		String dataTypeName = dataType.subSequence(dataType.lastIndexOf('.') + 1, dataType.length()).toString(); 
		
		if (false == _mappings.containsKey(dataType))
		{
			System.out.println("Unknown data received: " + dataTypeName);
			_connectionHandler.send("Unknown data received: " + dataTypeName);
		}
		
		System.out.println(dataTypeName + ": " + message.getData());
		System.out.println("");
		
		Object data = message.getData(_mappings.get(dataType));
		
		_connectionHandler.send(dataTypeName + " processed successfully");
	}
}
