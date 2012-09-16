package com.fARmework.HareAndHounds.Client.ViewModels;

import com.fARmework.HareAndHounds.Client.R;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Data.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

import android.view.*;
import gueei.binding.*;
import gueei.binding.observables.*;

public class GameModeViewModel extends ViewModel
{
	private ISettingsProvider _settingsProvider;
	
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(false);
	public BooleanObservable isConnected = new BooleanObservable(false);
	
	public Command newGame = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			if (!isConnected.get())
			{
				ConnectionManager.registerDataHandler(ConnectionSuccessInfo.class, new IDataHandler<ConnectionSuccessInfo>()
				{
					@Override
					public void handle(ConnectionSuccessInfo data)
					{
						isConnected.set(true);
						status.set(ResourcesProvider.getString(R.string.connection_success));
						isWaiting.set(false);
						ContextManager.navigateTo(HostingViewModel.class);
					}
				});
				
				connect();
			}
			else
			{
				ContextManager.navigateTo(HostingViewModel.class);
			}
		}
	};
	
	public Command joinGame = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			if (!isConnected.get())
			{
				ConnectionManager.registerDataHandler(ConnectionSuccessInfo.class, new IDataHandler<ConnectionSuccessInfo>()
				{
					@Override
					public void handle(ConnectionSuccessInfo data)
					{
						isConnected.set(true);
						status.set(ResourcesProvider.getString(R.string.connection_success));
						isWaiting.set(false);
						ContextManager.navigateTo(GameListViewModel.class);
					}
				});
				
				connect();
			}
			else
			{
				ContextManager.navigateTo(GameListViewModel.class);
			}
		}
	};
	
	public Command disconnect = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			disconnect();
		}
	};
	
	public Command options = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ContextManager.navigateTo(OptionsViewModel.class);
		}
	};
	
	@Inject
	public GameModeViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		ConnectionManager.registerDataHandler(ConnectionFaultInfo.class, new IDataHandler<ConnectionFaultInfo>()
		{
			@Override
			public void handle(ConnectionFaultInfo data)
			{
				status.set(ResourcesProvider.getString(R.string.connection_fault));
				isWaiting.set(false);
			}
		});
	}
	
	private void connect()
	{
		status.set(ResourcesProvider.getString(R.string.connection_connecting));
		isWaiting.set(true);
		ConnectionManager.connect(_settingsProvider.getServerAddress());
	}
	
	public void disconnect()
	{
		ConnectionManager.disconnect();
		isWaiting.set(false);
		
		if (isConnected.get())
		{
			isConnected.set(false);
			status.set(ResourcesProvider.getString(R.string.connection_disconnected));
		}
	}
	
	@Override
	public void onLeaving()
	{
		disconnect();
	}
}
