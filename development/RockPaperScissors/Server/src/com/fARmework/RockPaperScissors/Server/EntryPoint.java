package com.fARmework.RockPaperScissors.Server;

import java.util.ArrayList;

import com.fARmework.RockPaperScissors.Server.GuiceModules.*;
import com.fARmework.RockPaperScissors.Server.Logic.IGamesManager;
import com.fARmework.RockPaperScissors.Server.ScreenGestures.*;
import com.fARmework.core.data.IDataRegistry;
import com.fARmework.modules.ScreenGestures.Java.IGestureRegistry;
import com.fARmework.modules.ScreenGestures.Java.Matching.IPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing.IGestureProcessorFactory;
import com.google.inject.*;

public class EntryPoint
{
	public static void main(String[] args)
	{
		// register modules
		Injector injector = Guice.createInjector(getModules()); 
		
		// register data
		IDataRegistry dataRegistry = injector.getInstance(IDataRegistry.class);
		new com.fARmework.RockPaperScissors.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.ScreenGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		
		// configure modules
		configureScreenGestures(injector);
		
		// run
		injector.getInstance(IGamesManager.class).run();
	}
	
	private static Iterable<? extends Module> getModules()
	{
		ArrayList<AbstractModule> modules = new ArrayList<AbstractModule>();
		
		modules.add(new CoreModule());
		modules.add(new ScreenGesturesModule());
		modules.add(new LogicModule());
		
		return modules;
	}
	
	private static void configureScreenGestures(Injector injector)
	{
		IGestureRegistry gestureRegistry = injector.getInstance(IGestureRegistry.class);
		gestureRegistry.add(new RockScreenGesture());
		gestureRegistry.add(new PaperScreenGesture());
		gestureRegistry.add(new ScissorsScreenGesture());
		
		IGestureProcessorFactory processorFactory = injector.getInstance(IGestureProcessorFactory.class);
		
		
		IPatternMatcherFactory matcherFactory = injector.getInstance(IPatternMatcherFactory.class);
	}
}
