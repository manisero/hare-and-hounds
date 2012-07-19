package com.fARmework.core.server.impl;

import com.google.inject.*;

public class MainClass 
{
	public static void main(String[] args) 
	{
		Injector injector = Guice.createInjector(new ServerModule());
		
		UserInput userInput = injector.getInstance(UserInput.class);
		
		userInput.readAndSend();
	}
}
