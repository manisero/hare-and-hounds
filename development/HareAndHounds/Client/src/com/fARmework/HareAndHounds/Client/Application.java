package com.fARmework.HareAndHounds.Client;

import com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider;
import com.fARmework.HareAndHounds.Client.Infrastructure.ResourcesProvider;
import com.fARmework.core.data.IDataRegistry;
import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;
import com.fARmework.utils.Android.IContextManager;
import com.google.inject.Injector;

import gueei.binding.Binder;
import roboguice.RoboGuice;

public class Application extends android.app.Application
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
		injector.getInstance(ISettingsProvider.class).setContext(this);
		
		// Register data
		registerData(injector.getInstance(IDataRegistry.class));
		
		// Register views
		registerViews(injector.getInstance(IContextManager.class));
		
		// Configure modules
		configurePositionTracking(injector);
    }
	
	private void registerData(IDataRegistry dataRegistry)
	{
		new com.fARmework.HareAndHounds.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.PositionTracking.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
	}
	
	private void registerViews(IContextManager contextManager)
	{
	
	}
	
	private void configurePositionTracking(Injector injector)
	{
		injector.getInstance(ILocationManagerResolver.class).setContext(this);
	}
}
