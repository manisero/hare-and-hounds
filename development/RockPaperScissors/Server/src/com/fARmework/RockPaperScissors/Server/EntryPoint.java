package com.fARmework.RockPaperScissors.Server;

import com.fARmework.RockPaperScissors.Server.GuiceModules.*;
import com.fARmework.RockPaperScissors.Server.Logic.IGamesManager;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.DataHandler;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.EmptyDataHandler;
import com.fARmework.core.data.IDataRegistry;
import com.fARmework.core.server.Connection.IConnectionManager;
import com.fARmework.core.server.Data.ClientConnectedInfo;
import com.fARmework.core.server.Data.ClientDisconnectedInfo;
import com.fARmework.core.server.Data.ConnectionExceptionInfo;
import com.google.inject.*;

public class EntryPoint
{
	public static void main(String[] args)
	{
		// register modules
		Module coreModule = new CoreModule();
		Module logicModule = new LogicModule();
		Injector injector = Guice.createInjector(coreModule, logicModule); 
		
		// register data
		IDataRegistry dataRegistry = injector.getInstance(IDataRegistry.class);
		new com.fARmework.RockPaperScissors.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.ScreenGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		
		// register basic data handlers
		IConnectionManager connectionManager = injector.getInstance(IConnectionManager.class);
		connectionManager.registerDataHandler(ClientConnectedInfo.class, new EmptyDataHandler<ClientConnectedInfo>());
		connectionManager.registerDataHandler(ClientDisconnectedInfo.class, new EmptyDataHandler<ClientDisconnectedInfo>());
		connectionManager.registerDataHandler(ConnectionExceptionInfo.class, new DataHandler<ConnectionExceptionInfo>()
		{
			@Override
			public void handleData(int clientID, ConnectionExceptionInfo data)
			{
				System.out.println("Exception: " + data.Exception.getMessage());
			}
		});
		
		// run
		injector.getInstance(IGamesManager.class).run();
	}
}
