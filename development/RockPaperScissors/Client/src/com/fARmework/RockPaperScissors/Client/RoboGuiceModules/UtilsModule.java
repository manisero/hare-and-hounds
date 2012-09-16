package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.utils.Android.*;
import com.fARmework.utils.Android._impl.*;
import com.google.inject.AbstractModule;

public class UtilsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IContextManager.class).to(ContextManager.class).asEagerSingleton();
	}
}
