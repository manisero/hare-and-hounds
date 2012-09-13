package com.fARmework.HareAndHounds.Server;

import java.util.ArrayList;

import com.fARmework.HareAndHounds.Server.GuiceModules.*;
import com.fARmework.core.data.IDataRegistry;
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
		//injector.getInstance(IGamesManager.class).run();
	}
	
	private static Iterable<? extends Module> getModules()
	{
		ArrayList<AbstractModule> modules = new ArrayList<AbstractModule>();
		
		modules.add(new CoreModule());
		
		return modules;
	}
	
	private static void registerData(IDataRegistry dataRegistry)
	{
		new com.fARmework.HareAndHounds.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
	}
}
