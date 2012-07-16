package com.fARmework.RockPaperScissors.Server.Logic;

import com.fARmework.server.IMessageProcessor;
import com.fARmework.server.impl.Message;
import com.google.inject.Inject;

public class MessageProcessor implements IMessageProcessor
{
	private IConnectionHandler _connectionHandler;
	
	@Inject
	public MessageProcessor(IConnectionHandler connectionHandler)
	{
		_connectionHandler = connectionHandler;
	}
	
	@Override
	public void process(Message message)
	{
		System.out.println(message.getType() + ": " + message.getObject().toString());
		System.out.println("");
		
		_connectionHandler.send("ok, mam");
	}
}
