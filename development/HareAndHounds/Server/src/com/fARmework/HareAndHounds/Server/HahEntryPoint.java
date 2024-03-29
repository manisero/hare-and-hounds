package com.fARmework.HareAndHounds.Server;

import java.util.*;

import com.fARmework.HareAndHounds.Server.GuiceModules.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.data.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.core.server.Data.*;
import com.google.inject.*;

public class HahEntryPoint
{
	public static void main(String[] args)
	{
		// Register modules
		Injector injector = Guice.createInjector(getModules()); 
		
		// Register data
		registerData(injector.getInstance(IDataRegistry.class));
		
		// Run
		injector.getInstance(IGameListManager.class).run();
		
		// Register connection error handler
		registerConnectionExceptionHandler(injector.getInstance(IConnectionManager.class));
	}
	
	private static Iterable<? extends Module> getModules()
	{
		ArrayList<AbstractModule> modules = new ArrayList<AbstractModule>();
		
		modules.add(new CoreModule());
		modules.add(new UtilsModule());
		modules.add(new PositionTrackingModule());
		modules.add(new InfrastructureModule());
		modules.add(new LogicModule());
		
		return modules;
	}
	
	private static void registerData(IDataRegistry dataRegistry)
	{
		new com.fARmework.HareAndHounds.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.PositionTracking.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
	}
	
	private static void registerConnectionExceptionHandler(IConnectionManager connectionManager)
	{
		connectionManager.registerDataHandler(ConnectionExceptionInfo.class, new IDataHandler<ConnectionExceptionInfo>()
		{
			@Override
			public void handle(int clientID, ConnectionExceptionInfo data)
			{
				data.Exception.printStackTrace();
			}
		});
	}
}
