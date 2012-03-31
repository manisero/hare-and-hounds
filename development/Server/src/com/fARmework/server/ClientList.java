package com.fARmework.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientList 
{
	private static final ClientList _instance = new ClientList();
	
	private List<Socket> _sockets;
	
	private ClientList()
	{
		_sockets = new ArrayList<Socket>();
	}
	
	public static ClientList getInstance()
	{
		return _instance;
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
