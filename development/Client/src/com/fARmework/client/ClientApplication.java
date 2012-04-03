package com.fARmework.client;

import gueei.binding.Binder;
import roboguice.RoboGuice;
import android.app.Application;

import com.google.inject.Inject;

public class ClientApplication extends Application
{
	@Inject
	ResourcesProvider resourcesProvider;
	
	@Override
    public void onCreate()
	{
		super.onCreate();
		
		// Initialize android-binding
		Binder.init(this);
		
		// Initialize ResourcesProvider
		RoboGuice.injectMembers(this, this);
		resourcesProvider.setResources(getResources());
    }
}
