package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities.IFileIndexer;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities.IFileUtilities;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities._impl.FileIndexer;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Utilities._impl.FileUtilities;
import com.google.inject.AbstractModule;

public class UtilitiesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IFileIndexer.class).to(FileIndexer.class);
		bind(IFileUtilities.class).to(FileUtilities.class);
	}
}
