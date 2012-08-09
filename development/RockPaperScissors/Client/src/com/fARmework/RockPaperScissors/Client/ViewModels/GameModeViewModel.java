package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.IObservable;
import gueei.binding.Observer;
import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

import java.util.Collection;

import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.fARmework.core.client.Data.ConnectionFaultInfo;
import com.fARmework.core.client.Data.ConnectionSuccessInfo;
import com.google.inject.Inject;

public class GameModeViewModel extends ViewModel
{
	public StringObservable serverAddress = new StringObservable();
	public StringObservable userName = new StringObservable();
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
	
	// NOTE: Is is recommended that Observers are declared as fields so that reference to them is kept
	private Observer _serverAddressObserver = new Observer()
	{
		@Override
		public void onPropertyChanged(IObservable<?> arg0, Collection<Object> arg1)
		{
			_settingsProvider.setServerAddress((String)arg0.get());
		}
	};
	
	private Observer _userNameObserver = new Observer()
	{
		@Override
		public void onPropertyChanged(IObservable<?> arg0, Collection<Object> arg1)
		{
			_settingsProvider.setUserName((String)arg0.get());
		}
	};
	
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public GameModeViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		serverAddress.set(_settingsProvider.getServerAddress());
		serverAddress.subscribe(_serverAddressObserver);
		
		userName.set(_settingsProvider.getUserName());
		userName.subscribe(_userNameObserver);
		
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
		ConnectionManager.connect(serverAddress.get());
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
}
