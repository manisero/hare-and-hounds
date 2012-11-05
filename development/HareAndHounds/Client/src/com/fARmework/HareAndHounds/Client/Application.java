package com.fARmework.HareAndHounds.Client;

import gueei.binding.Binder;
import roboguice.*;

import com.fARmework.HareAndHounds.Client.Activities.*;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider;
import com.fARmework.HareAndHounds.Client.RoboGuiceModules.*;
import com.fARmework.HareAndHounds.Client.ViewModels.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Data.*;
import com.fARmework.core.data.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure.IContextManager.IDialogListener;
import com.fARmework.utils.Android.Media.*;
import com.fARmework.utils.Android.RoboGuice.*;
import com.google.inject.*;
import com.google.inject.util.*;

public class Application extends android.app.Application
{
	@Override
    public void onCreate()
	{
		super.onCreate();
		
		// Initialize android-binding
		Binder.init(this);
		
		// Configure RoboGuice
		Injector injector = RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, Modules.override(RoboGuice.newDefaultRoboModule(this)).with(new CoreModule(), new PositionTrackingModule(), new HareAndHoundsModule(), new UtilsModule())); // RoboGuice.getInjector(this);
		
		// Initialize ResourcesProvider and SettingsProvider
		ResourcesProvider.setResources(getResources());
		injector.getInstance(ISettingsProvider.class).setContext(this);
		
		// Register data
		registerData(injector.getInstance(IDataRegistry.class));
		
		// Register views
		registerViews(injector.getInstance(IContextManager.class));
		
		// Register context
		injector.getInstance(IContextProvider.class).set(this);
		
		// Configure modules
		configureUtils(injector);
		
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
	
	private void configureUtils(Injector injector)
	{
		injector.getInstance(ISoundPoolManager.class).setContext(this); // TODO: remove
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
