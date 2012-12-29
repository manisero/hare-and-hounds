package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure._impl.*;
import com.google.inject.AbstractModule;

public class RockPaperScissorsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// Infrastructure
		bind(ISettingsProvider.class).to(SettingsProvider.class).asEagerSingleton();
	}
}
