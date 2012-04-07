package com.fARmework.client;

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
		
		// Initialize ResourcesProvider
		RoboGuice.getInjector(this).getInstance(ResourcesProvider.class).setResources(getResources());
    }
}
