package com.fARmework.RockPaperScissors.Server;

import java.util.ArrayList;

import com.fARmework.RockPaperScissors.Server.Gestures.ScreenGestures.*;
import com.fARmework.RockPaperScissors.Server.Gestures.SpaceGestures.RockSpaceGesture;
import com.fARmework.RockPaperScissors.Server.GuiceModules.*;
import com.fARmework.RockPaperScissors.Server.Logic.IGamesManager;
import com.fARmework.core.data.IDataRegistry;
import com.fARmework.modules.ScreenGestures.Java.IScreenGestureRegistry;
import com.fARmework.modules.ScreenGestures.Java.Matching.IScreenPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.DiffusedScreenPatternMatcher;
import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.PlainScreenPatternMatcher;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessorFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing.GestureProcessors.*;
import com.fARmework.modules.SpaceGestures.Java.ISpaceGestureRegistry;
import com.fARmework.modules.SpaceGestures.Java.Matching.ISpacePatternMatcherFactory;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.PlainSpacePatternMatcher;
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
		gestureRegistry.register(new RockScreenGesture());
		gestureRegistry.register(new PaperScreenGesture());
		gestureRegistry.register(new ScissorsScreenGesture());
		
		IScreenGestureProcessorFactory processorFactory = injector.getInstance(IScreenGestureProcessorFactory.class);
		processorFactory.register(RockScreenGesture.class, new PlainScreenGestureProcessor());
		processorFactory.register(PaperScreenGesture.class, new PlainScreenGestureProcessor());
		processorFactory.register(ScissorsScreenGesture.class, new DiffusedScreenGestureProcessor());
		
		IScreenPatternMatcherFactory matcherFactory = injector.getInstance(IScreenPatternMatcherFactory.class);
		matcherFactory.register(RockScreenGesture.class, new PlainScreenPatternMatcher());
		matcherFactory.register(PaperScreenGesture.class, new PlainScreenPatternMatcher());
		matcherFactory.register(ScissorsScreenGesture.class, new DiffusedScreenPatternMatcher());
	}
	
	private static void configureSpaceGestures(Injector injector)
	{
		ISpaceGestureRegistry gestureRegistry = injector.getInstance(ISpaceGestureRegistry.class);
		gestureRegistry.register(new RockSpaceGesture());
		
		ISpacePatternMatcherFactory matcherFactory = injector.getInstance(ISpacePatternMatcherFactory.class);
		matcherFactory.register(RockSpaceGesture.class, new PlainSpacePatternMatcher());
	}
}
