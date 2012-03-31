package com.fARmework.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReader implements Runnable
{
	Socket _clientSocket;
	ClientList _clientList;
	
	public ClientReader(Socket clientSocket)
	{
		_clientList = ClientList.getInstance();
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
