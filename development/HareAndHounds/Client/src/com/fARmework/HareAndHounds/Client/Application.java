package com.fARmework.HareAndHounds.Client;

import com.fARmework.HareAndHounds.Client.Activities.*;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.ViewModels.*;
import com.fARmework.core.data.*;
import com.fARmework.modules.PositionTracking.Android.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

import roboguice.*;
import gueei.binding.Binder;

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
		new com.fARmework.modules.PositionTracking.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.HareAndHounds.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
	}
	
	private void registerViews(IContextManager contextManager)
	{
		contextManager.registerView(GameModeViewModel.class, GameModeActivity.class, R.layout.game_mode);
		contextManager.registerView(OptionsViewModel.class, OptionsActivity.class, R.layout.options);
		contextManager.registerView(HostingViewModel.class, HostingActivity.class, R.layout.hosting);
		contextManager.registerView(GameListViewModel.class, GameListActivity.class, R.layout.game_list);
	}
	
	private void configurePositionTracking(Injector injector)
	{
		injector.getInstance(ILocationManagerResolver.class).setContext(this);
	}
}
