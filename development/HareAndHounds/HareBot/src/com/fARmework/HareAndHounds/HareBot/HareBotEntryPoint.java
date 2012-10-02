package com.fARmework.HareAndHounds.HareBot;

import com.fARmework.HareAndHounds.HareBot.GuiceModules.*;
import com.fARmework.HareAndHounds.HareBot.Logic.*;
import com.fARmework.core.data.*;
import com.google.inject.*;

import java.util.*;

public class HareBotEntryPoint
{
	public static void main(String[] args)
	{
		// Register modules
		Injector injector = Guice.createInjector(getModules()); 
		
		// Register data
		registerData(injector.getInstance(IDataRegistry.class));
		
		// Run
		injector.getInstance(IHareBotManager.class).runBot();
	}
	
	private static Iterable<? extends Module> getModules()
	{
		ArrayList<AbstractModule> modules = new ArrayList<AbstractModule>();
		
		modules.add(new CoreModule());	
		modules.add(new InfrastructureModule());
		modules.add(new LogicModule());
		
		return modules;
	}	

	private static void registerData(IDataRegistry dataRegistry)
	{
		new com.fARmework.modules.PositionTracking.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
	}
}
