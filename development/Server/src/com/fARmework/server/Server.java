package com.fARmework.server;

public class Server 
{
	public static void main(String[] args)
	{	
		ServerListener listener = new ServerListener();
		
		listener.acceptConnections();
	}
}
