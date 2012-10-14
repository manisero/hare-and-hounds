package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure._impl.*;
import com.fARmework.utils.Android.Media.*;
import com.fARmework.utils.Android.Media._impl.*;
import com.google.inject.AbstractModule;

public class UtilsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		// Infrastructure
		bind(ISettingsProvider.class).to(com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider.class);
		bind(IContextManager.class).to(ContextManager.class).asEagerSingleton();
		
		// Media
		bind(ISoundPlayer.class).to(SoundPlayer.class);
	}
}
