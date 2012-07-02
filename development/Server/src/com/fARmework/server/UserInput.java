package com.fARmework.server;

import java.io.*;

public class UserInput 
{
	private Server _server;
	
	private MessageFactory _factory;
	
	public UserInput()
	{
		_server = new Server("6969");
		
		_server.start();
		
		_factory = new MessageFactory();
		
		_factory.register(String.class, "STRING_MESSAGE");
	}
	
	public void readAndSend()
	{
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
