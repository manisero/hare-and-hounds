package com.fARmework.RockPaperScissors.Client;

import com.fARmework.core.data.IDataRegistry;
import com.fARmework.modules.ScreenGestures.Data.DataRegistrar;
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
		
		// Initialize SettingsProvider and ResourcesProvider
		injector.getInstance(SettingsProvider.class).setResources(getResources());
		injector.getInstance(ResourcesProvider.class).setResources(getResources());
		
		// Register data
		IDataRegistry dataRegistry = injector.getInstance(IDataRegistry.class);
		new DataRegistrar().registerData(dataRegistry);
    }
}
