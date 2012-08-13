package com.fARmework.RockPaperScissors.Server;

import com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures.*;
import com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures.*;
import com.fARmework.RockPaperScissors.Server.GuiceModules.*;
import com.fARmework.RockPaperScissors.Server.Logic.*;
import com.fARmework.core.data.*;
import com.fARmework.modules.ScreenGestures.Java.*;
import com.fARmework.modules.ScreenGestures.Java.Matching.*;
import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.GestureProcessors.*;
import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.*;
import com.google.inject.*;
import java.util.*;

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
		new com.fARmework.modules.SpaceGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		
		// configure modules
		configureScreenGestures(injector);
		configureSpaceGestures(injector);
		
		// run
		injector.getInstance(IGamesManager.class).run();
	}
	
	private static Iterable<? extends Module> getModules()
	{
		ArrayList<AbstractModule> modules = new ArrayList<AbstractModule>();
		
		modules.add(new CoreModule());
		modules.add(new ScreenGesturesModule());
		modules.add(new SpaceGesturesModule());
		modules.add(new LogicModule());
		
		return modules;
	}
	
	private static void configureScreenGestures(Injector injector)
	{
		IScreenGestureRegistry gestureRegistry = injector.getInstance(IScreenGestureRegistry.class);
		gestureRegistry.register(new PaperScreenGesture());
		gestureRegistry.register(new ScissorsScreenGesture());
		gestureRegistry.register(new RockScreenGesture());
		
		IScreenGestureProcessorFactory processorFactory = injector.getInstance(IScreenGestureProcessorFactory.class);
		processorFactory.register(PaperScreenGesture.class, new PlainScreenGestureProcessor());
		processorFactory.register(ScissorsScreenGesture.class, new DiffusedScreenGestureProcessor());
		processorFactory.register(RockScreenGesture.class, new GroupedScreenGestureProcessor());
		
		IScreenPatternMatcherFactory matcherFactory = injector.getInstance(IScreenPatternMatcherFactory.class);
		matcherFactory.register(PaperScreenGesture.class, new PlainScreenPatternMatcher());
		matcherFactory.register(ScissorsScreenGesture.class, new DiffusedScreenPatternMatcher());
		matcherFactory.register(RockScreenGesture.class, new GroupedScreenPatternMatcher());
	}
	
	private static void configureSpaceGestures(Injector injector)
	{
		ISpaceGestureRegistry gestureRegistry = injector.getInstance(ISpaceGestureRegistry.class);
		gestureRegistry.register(new RockSpaceGesture());
		
		ISpacePatternMatcherFactory matcherFactory = injector.getInstance(ISpacePatternMatcherFactory.class);
		matcherFactory.register(RockSpaceGesture.class, new PlainSpacePatternMatcher());
	}
}
