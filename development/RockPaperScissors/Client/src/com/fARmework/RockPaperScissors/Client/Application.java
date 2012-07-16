package com.fARmework.RockPaperScissors.Client;

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
		
		// Initialize SettingsProvider and ResourcesProvider
		RoboGuice.getInjector(this).getInstance(SettingsProvider.class).setResources(getResources());
		RoboGuice.getInjector(this).getInstance(ResourcesProvider.class).setResources(getResources());
    }
}
