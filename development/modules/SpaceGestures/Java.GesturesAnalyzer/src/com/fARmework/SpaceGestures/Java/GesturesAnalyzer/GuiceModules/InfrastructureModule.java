package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Infrastructure.ISettingsProvider;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Infrastructure._impl.SettingsProvider;
import com.fARmework.utils.Java.ISettingsReader;
import com.fARmework.utils.Java._impl.SettingsReader;
import com.google.inject.AbstractModule;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsReader.class).to(SettingsReader.class);
		bind(ISettingsProvider.class).to(SettingsProvider.class);
	}
}
