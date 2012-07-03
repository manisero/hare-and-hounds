package com.fARmework.server.impl;

import com.fARmework.server.*;

public class DummyMessageProcessor implements IMessageProcessor 
{
	@Override
	public void process(Message message) 
	{
		System.out.println("Message type: " + message.getType());
		
		System.out.println("Message body: " + message.getObject().toString());
		
		System.out.println("");
	}
}
