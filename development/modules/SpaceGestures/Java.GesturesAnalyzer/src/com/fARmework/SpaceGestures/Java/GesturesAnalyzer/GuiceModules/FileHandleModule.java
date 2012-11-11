package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.IDataExporter;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers.IDataLoader;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers._impl.DataExporter;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.FileHandlers._impl.DataLoader;
import com.google.inject.AbstractModule;

public class FileHandleModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IDataLoader.class).to(DataLoader.class);
		bind(IDataExporter.class).to(DataExporter.class);
	}
}
