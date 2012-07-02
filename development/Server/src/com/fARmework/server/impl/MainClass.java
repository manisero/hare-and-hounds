package com.fARmework.server.impl;

import com.google.inject.*;

public class MainClass 
{
	public static void main(String[] args) 
	{
		Injector injector = Guice.createInjector(new ServerModule());
		
		registerMessageTypes(
				injector.getInstance(MessageFactory.class));
		
		UserInput userInput = injector.getInstance(UserInput.class);
		
		userInput.readAndSend();
	}
	
	private static void registerMessageTypes(MessageFactory factory)
	{
		factory.register(String.class, "STRING_MESSAGE");
	}
}
