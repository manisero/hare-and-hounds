package com.fARmework.client.UI;

import gueei.binding.Binder;
import roboguice.RoboGuice;
import android.app.Application;

public class ClientApplication extends Application
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
