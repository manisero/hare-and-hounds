package com.fARmework.HareAndHounds.HareBot.GuiceModules;

import com.fARmework.HareAndHounds.HareBot.Infrastructure.*;
import com.fARmework.HareAndHounds.HareBot.Infrastructure._impl.*;
import com.fARmework.utils.Java.*;
import com.fARmework.utils.Java._impl.*;
import com.google.inject.*;

public class InfrastructureModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(SettingsProvider.class).asEagerSingleton();
		bind(ISettingsReader.class).to(SettingsReader.class);
	}
}
