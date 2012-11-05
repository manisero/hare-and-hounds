package com.fARmework.HareAndHounds.Client;

import gueei.binding.Binder;
import roboguice.*;

import com.fARmework.HareAndHounds.Client.Activities.*;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider;
import com.fARmework.HareAndHounds.Client.ViewModels.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Data.*;
import com.fARmework.core.data.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure.IContextManager.IDialogListener;
import com.google.inject.*;

public class Application extends android.app.Application
{
	@Override
    public void onCreate()
	{
		super.onCreate();
		
		// Initialize android-binding
		Binder.init(this);
		
		// Get RoboGuice injector
		Injector injector = RoboGuice.getInjector(this);
		
		// Initialize ResourcesProvider and SettingsProvider
		ResourcesProvider.setResources(getResources());
		injector.getInstance(ISettingsProvider.class).setContext(this);
		
		// Register data
		registerData(injector.getInstance(IDataRegistry.class));
		
		// Register views
		registerViews(injector.getInstance(IContextManager.class));
		
		// Register connection error handler
		registerConnectionExceptionHandler(injector.getInstance(IConnectionManager.class), injector.getInstance(IContextManager.class));
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
		
		contextManager.registerView(HareViewModel.class, HareActivity.class, R.layout.hare);
		contextManager.registerView(HoundsViewModel.class, HoundsActivity.class, R.layout.hounds);
		contextManager.registerView(CheckpointViewModel.class, CheckpointActivity.class, R.layout.checkpoint);
	}
	
	private void registerConnectionExceptionHandler(IConnectionManager connectionManager, final IContextManager contextManager)
	{
		connectionManager.registerDataHandler(ConnectionExceptionInfo.class, new IDataHandler<ConnectionExceptionInfo>()
		{
			@Override
			public void handle(ConnectionExceptionInfo data)
			{
				contextManager.showDialogNotification(ResourcesProvider.getString(R.string.connection_exception),
													  ResourcesProvider.getString(R.string.dialog_confirm),
													  new IDialogListener()
													  {
														  @Override
														  public void onDialogResult()
														  {
															  contextManager.finishApplication();
														  }
													  });
			}
		});
	}
}
