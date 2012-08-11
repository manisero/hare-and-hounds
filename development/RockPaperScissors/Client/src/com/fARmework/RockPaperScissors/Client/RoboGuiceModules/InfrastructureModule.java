package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.ContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.SettingsProvider;
import com.google.inject.AbstractModule;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(SettingsProvider.class).asEagerSingleton();
		bind(IContextManager.class).to(ContextManager.class).asEagerSingleton();
	}
}
