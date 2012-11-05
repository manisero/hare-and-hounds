package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import android.content.*;

import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure._impl.*;
import com.fARmework.utils.Android.RoboGuice.*;
import com.google.inject.AbstractModule;

public class RockPaperScissorsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// RoboGuice
		bind(Context.class).toProvider(IContextProvider.class);
		
		// Infrastructure
		bind(ISettingsProvider.class).to(SettingsProvider.class).asEagerSingleton();
	}
}
