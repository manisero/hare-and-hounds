package com.fARmework.server;

import java.io.*;
import java.net.*;

public class ClientReader implements Runnable
{
	private ClientList _clientList;
	
	private Socket _clientSocket;
	
	public ClientReader(ClientList clientList, Socket clientSocket)
	{
		_clientList = clientList;
		
		_clientSocket = clientSocket;
	}
	
	@Override
	public void run() 
	{
		BufferedReader in = null;
		
		try
		{
			in = new BufferedReader(	new InputStreamReader(
										_clientSocket.getInputStream()));
		}
		catch(IOException e)
		{
			System.err.println("Could not obtain client's input stream");
		}
		
		String clientInput;
		
		try
		{
			while((clientInput = in.readLine()) != null)
			{
				System.out.println(clientInput);
			}
		}
		catch(IOException e)
		{
			_clientList.removeClient(_clientSocket);
			
			return;
		}
	}
}
