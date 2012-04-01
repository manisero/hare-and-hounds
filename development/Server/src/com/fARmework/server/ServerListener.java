package com.fARmework.server;

import java.io.*;
import java.net.*;
import com.google.inject.*;

public class ServerListener 
{
	private static final int PORT = 6666;
	
	private ClientList _clientList;
	
	@Inject
	public ServerListener(ClientList clientList)
	{
		_clientList = clientList;
	}
	
	public void acceptConnections()
	{
		ServerSocket serverSocket = initializeServerSocket();
		
		initializeWriterThread();
		
		Socket clientSocket = null;
		
		while(true)
		{
			clientSocket = acceptClient(serverSocket);
			
			_clientList.addClient(clientSocket);
			
			(new Thread(new ClientReader(_clientList, clientSocket))).start();
		}
	}
	
	private ServerSocket initializeServerSocket()
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
		
		return serverSocket;
	}
	
	private void initializeWriterThread()
	{
		ClientWriter writer = new ClientWriter(_clientList);
		
		(new Thread(writer)).start();
	}
	
	private Socket acceptClient(ServerSocket serverSocket)
	{
		Socket clientSocket = null;
		
		try
		{
			clientSocket = serverSocket.accept(); 
		}
		catch(IOException e)
		{
			System.err.print("Could not perform accept");
			
			System.exit(1);
		}
		
		return clientSocket;
	}
}
