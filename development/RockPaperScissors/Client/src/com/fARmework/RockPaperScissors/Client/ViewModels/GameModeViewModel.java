package com.fARmework.RockPaperScissors.Client.ViewModels;

import java.util.Collection;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Data.GameCreationInfo;
import com.fARmework.RockPaperScissors.Data.GameCreationRequest;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.fARmework.core.client.Data.ConnectionFaultInfo;
import com.fARmework.core.client.Data.ConnectionSuccessInfo;
import com.google.inject.Inject;

import android.view.View;
import gueei.binding.Command;
import gueei.binding.IObservable;
import gueei.binding.Observer;
import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

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
						createGame();
					}
				});
				
				connect();
			}
			else
			{
				createGame();
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
						NavigationManager.navigateTo(GameListViewModel.class);
					}
				});
				
				connect();
			}
			else
			{
				NavigationManager.navigateTo(GameListViewModel.class);
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
	
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public GameModeViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
		_settingsProvider = settingsProvider;
		
		serverAddress.set(_settingsProvider.getServerAddress());
		
		serverAddress.subscribe(new Observer()
		{
			@Override
			public void onPropertyChanged(IObservable<?> arg0, Collection<Object> arg1)
			{
				_settingsProvider.setServerAddress((String)arg0.get());
			}
		});
		
		userName.set(_settingsProvider.getUserName());
		
		userName.subscribe(new Observer()
		{
			@Override
			public void onPropertyChanged(IObservable<?> arg0, Collection<Object> arg1)
			{
				_settingsProvider.setUserName((String)arg0.get());
			}
		});
		
		ConnectionManager.registerDataHandler(ConnectionFaultInfo.class, new IDataHandler<ConnectionFaultInfo>()
		{
			@Override
			public void handle(ConnectionFaultInfo data)
			{
				status.set(ResourcesProvider.getString(R.string.connection_fault));
				isWaiting.set(false);
			}
		});
		
		ConnectionManager.registerDataHandler(GameCreationInfo.class, new IDataHandler<GameCreationInfo>()
		{
			@Override
			public void handle(GameCreationInfo data)
			{
				status.set(ResourcesProvider.getString(R.string.hosting_created));
				isWaiting.set(false);
				NavigationManager.navigateTo(HostingViewModel.class);
			}
		});
	}
	
	private void connect()
	{
		status.set(ResourcesProvider.getString(R.string.connection_connecting));
		isWaiting.set(true);
		ConnectionManager.connect(serverAddress.get());
	}
	
	private void createGame()
	{
		status.set(ResourcesProvider.getString(R.string.hosting_creating));
		ConnectionManager.send(new GameCreationRequest(userName.get()));
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
