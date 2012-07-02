package com.fARmework.server;

import java.io.*;

public class UserInput 
{
	private IMessanger _messanger;
	
	private MessageFactory _factory;
	
	public UserInput(IMessanger messanger)
	{
		_messanger = messanger;
		
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
				
				_messanger.send(_factory.getMessage(message));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
