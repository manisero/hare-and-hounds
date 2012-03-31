package com.fARmework.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener 
{
	private static final int PORT = 6666;
	
	private ClientList _clientList;
	
	public ServerListener()
	{
		_clientList = ClientList.getInstance();
	}
	
	public void acceptConnections()
	{
		ServerSocket serverSocket = null;
		
		try
		{
			serverSocket = new ServerSocket(PORT);
		}
		catch(IOException e)
		{
			System.err.print("Could not allocate port: " + PORT);
			System.exit(1);
		}
		
		ClientWriter writer = new ClientWriter();
		
		(new Thread(writer)).start();
		
		Socket clientSocket = null;
		
		while(true)
		{
			try
			{
				clientSocket = serverSocket.accept(); 
			}
			catch(IOException e)
			{
				System.err.print("Could not perform accept");
				System.exit(1);
			}
			
			_clientList.addClient(clientSocket);
			
			(new Thread(new ClientReader(clientSocket))).start();
		}
	}
}
