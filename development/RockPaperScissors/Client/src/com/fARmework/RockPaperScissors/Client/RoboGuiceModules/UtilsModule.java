package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure._impl.*;
import com.fARmework.utils.Android.RoboGuice.*;
import com.fARmework.utils.Android.RoboGuice._impl.*;
import com.google.inject.AbstractModule;

public class UtilsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// RoboGuice
		bind(IContextProvider.class).to(ContextProvider.class).asEagerSingleton();
		
		// Infrastructure
		bind(IContextManager.class).to(ContextManager.class).asEagerSingleton();
		bind(ISettingsProvider.class).to(com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider.class);
	}
}
