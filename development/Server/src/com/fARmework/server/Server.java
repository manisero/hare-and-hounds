package com.fARmework.server;

import com.google.inject.*;

public class Server 
{
	public static void main(String[] args)
	{	
		Injector injector = Guice.createInjector();
		
		ServerListener listener = injector.getInstance(ServerListener.class);
		
		listener.acceptConnections();
	}
}
