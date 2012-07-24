package com.fARmework.core.server.impl;

import com.google.inject.*;
import java.io.*;

public class UserInput 
{
	private ConnectionManager _server;
	
	@Inject
	public UserInput(ConnectionManager server)
	{
		_server = server;
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
				_server.send(bufferedReader.readLine());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
