package com.fARmework.RockPaperScissors.Client;

import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.SettingsProvider;
import com.fARmework.core.data.IDataRegistry;
import com.google.inject.Injector;

import gueei.binding.Binder;
import roboguice.RoboGuice;

public class Application extends android.app. Application
{
	@Override
    public void onCreate()
	{
		super.onCreate();
		
		// Initialize android-binding
		Binder.init(this);
		
		Injector injector = RoboGuice.getInjector(this);
		
		// Initialize ResourcesProvider and SettingsProvider
		ResourcesProvider.setResources(getResources());
		injector.getInstance(SettingsProvider.class).setResources(getResources());
		
		// Register data
		IDataRegistry dataRegistry = injector.getInstance(IDataRegistry.class);
		new com.fARmework.RockPaperScissors.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.ScreenGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
    }
}
