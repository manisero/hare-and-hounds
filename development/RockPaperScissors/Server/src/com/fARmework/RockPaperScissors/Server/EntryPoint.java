package com.fARmework.RockPaperScissors.Server;

import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.server.impl.ServerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

public class EntryPoint
{
	public static void main(String[] args) 
	{
		Module module = Modules.override(new ServerModule()).with(new LogicModule()); 
		Injector injector = Guice.createInjector(module); 
		
		IConnectionHandler connectionHandler = injector.getInstance(IConnectionHandler.class);
		connectionHandler.connect();
	}
}
