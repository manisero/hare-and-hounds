package com.fARmework.server.impl;

import com.google.inject.*;
import java.io.*;

public class UserInput 
{
	private Server _server;
	
	@Inject
	public UserInput(Server server)
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
