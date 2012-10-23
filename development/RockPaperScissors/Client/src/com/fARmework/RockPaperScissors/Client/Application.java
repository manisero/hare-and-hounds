package com.fARmework.RockPaperScissors.Client;

import com.fARmework.RockPaperScissors.Client.Activities.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.ViewModels.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Data.*;
import com.fARmework.core.data.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure.IContextManager.*;
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
		
		// Register connection error handler
		registerConnectionExceptionHandler(injector.getInstance(IConnectionManager.class), injector.getInstance(IContextManager.class));
    }
	
	private void registerData(IDataRegistry dataRegistry)
	{
		new com.fARmework.RockPaperScissors.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.ScreenGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.SpaceGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
	}
	
	private void registerViews(IContextManager contextManager)
	{
		contextManager.registerView(GameModeViewModel.class, GameModeActivity.class, R.layout.game_mode);
		contextManager.registerView(OptionsViewModel.class, OptionsActivity.class, R.layout.options);
		contextManager.registerView(HostingViewModel.class, HostingActivity.class, R.layout.hosting);
		contextManager.registerView(GameListViewModel.class, GameListActivity.class, R.layout.game_list);
		contextManager.registerView(GameViewModel.class, GameActivity.class, R.layout.game);
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
