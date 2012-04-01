package com.fARmework.server;

import java.net.*;
import java.util.*;
import com.google.inject.*;

@Singleton
public class ClientList 
{	
	private List<Socket> _sockets;
	
	public ClientList()
	{
		_sockets = new ArrayList<Socket>();
	}
	
	public synchronized void addClient(Socket clientSocket)
	{
		_sockets.add(clientSocket);
	}
	
	public synchronized void removeClient(Socket clientSocket)
	{
		_sockets.remove(clientSocket);
	}
	
	public synchronized List<Socket> getClientsCopy()
	{
		List<Socket> sockets = new ArrayList<Socket>(_sockets);
		
		return sockets;
	}
}
