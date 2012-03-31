package com.fARmework.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientWriter implements Runnable 
{
	private ClientList _clientList;
	
	public ClientWriter()
	{
		_clientList = ClientList.getInstance();
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
