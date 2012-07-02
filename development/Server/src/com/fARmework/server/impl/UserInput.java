package com.fARmework.server.impl;

import com.google.inject.*;
import java.io.*;

public class UserInput 
{
	private Server _server;
	
	private MessageFactory _factory;
	
	@Inject
	public UserInput(Server server, MessageFactory factory)
	{
		_server = server;
		
		_factory = factory;
		
		_factory.register(String.class, "STRING_MESSAGE");
	}
	
	public void readAndSend()
	{
		_server.start();
		
		BufferedReader bufferedReader = new BufferedReader(
												new InputStreamReader(
														System.in));
		while(true)
		{
			try 
			{
				String message = bufferedReader.readLine();
				
				_server.send(_factory.getMessage(message));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
