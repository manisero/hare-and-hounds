package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.*;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.core.client.Data.*;
import com.google.inject.Inject;

public class GameModeViewModel extends ViewModel
{
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(false);
	public BooleanObservable isConnected = new BooleanObservable(false);
	
	public Command create = new Command()
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
	
	public Command join = new Command()
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
	
	private ISettingsProvider _settingsProvider;
	
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
