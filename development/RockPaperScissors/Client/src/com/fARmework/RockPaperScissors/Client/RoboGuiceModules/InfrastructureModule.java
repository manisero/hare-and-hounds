package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.ActivitiesManager;
import com.google.inject.AbstractModule;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IActivitiesManager.class).to(ActivitiesManager.class).asEagerSingleton();
	}
}
