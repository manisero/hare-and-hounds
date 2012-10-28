package com.fARmework.SpaceGestures.Java.GesturesAnalyzer;

import java.util.ArrayList;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Analyzer.IGesturesAnalyzer;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules.FileHandleModule;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules.InfrastructureModule;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules.LogicModule;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules.UtilitiesModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class EntryPoint
{
	public static void main(String[] args)
	{
		// Register modules
		Injector injector = Guice.createInjector(getModules()); 
		
		// Run
		injector.getInstance(IGesturesAnalyzer.class).run();
	}
	
	private static Iterable<? extends Module> getModules()
	{
		ArrayList<AbstractModule> modules = new ArrayList<AbstractModule>();
		
		modules.add(new LogicModule());
		modules.add(new FileHandleModule());
		modules.add(new UtilitiesModule());
		modules.add(new InfrastructureModule());
		
		return modules;
	}
}
