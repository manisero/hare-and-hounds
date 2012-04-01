package com.fARmework.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientWriter implements Runnable 
{
	private ClientList _clientList;
	
	public ClientWriter(ClientList clientList)
	{
		_clientList = clientList;
	}
	
	@Override
	public void run() 
	{
		BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));
		
		List<Socket> sockets;
		
		PrintWriter out = null;
		
		String input;
		
		try
		{
			while((input = standardInput.readLine()) != null)
			{
				sockets = _clientList.getClientsCopy();
				
				for(Socket s : sockets)
				{
					out = new PrintWriter(s.getOutputStream(), true);
					
					out.println(input);
				}
			}
		}
		catch(IOException e)
		{
			System.out.println("Failed writing to a client");
			
			return;
		}	
	}
}
