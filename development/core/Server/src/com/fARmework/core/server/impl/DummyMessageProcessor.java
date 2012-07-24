package com.fARmework.core.server.impl;

import com.fARmework.core.data.Message;
import com.fARmework.core.server.*;

public class DummyMessageProcessor implements IMessageProcessor 
{
	@Override
	public void process(Message message) 
	{
		System.out.println("Message type: " + message.getType());
		
		System.out.println("Message body: " + message.getData());
		
		System.out.println("");
	}
}
