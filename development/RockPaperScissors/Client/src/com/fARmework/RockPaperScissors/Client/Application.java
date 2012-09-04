package com.fARmework.RockPaperScissors.Client;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager.IDialogListener;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.fARmework.core.client.Data.ConnectionExceptionInfo;
import com.fARmework.core.data.IDataRegistry;
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
		IDataRegistry dataRegistry = injector.getInstance(IDataRegistry.class);
		new com.fARmework.RockPaperScissors.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.ScreenGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.SpaceGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		
		// Register connection error handling
		injector.getInstance(IConnectionManager.class).registerDataHandler(ConnectionExceptionInfo.class, new IDataHandler<ConnectionExceptionInfo>()
		{
			@Override
			public void handle(ConnectionExceptionInfo data)
			{
				RoboGuice.getInjector(Application.this).getInstance(IContextManager.class)
					.showDialogNotification(ResourcesProvider.getString(R.string.connection_exception), new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							throw new RuntimeException();
						}
					});
			}
		});
    }
}
