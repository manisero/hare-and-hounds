package com.fARmework.RockPaperScissors.Client.ViewModels;

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
import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;

public class GameModeViewModel extends ViewModel
{
	public StringObservable serverAddress = new StringObservable();
	public StringObservable userName = new StringObservable();
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(false);
	
	public Command create = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_isHost = true;
			connect();
		}
	};
	
	public Command join = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_isHost = false;
			connect();
		}
	};
	
	private boolean _isHost;
	
	@Inject
	public GameModeViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
		serverAddress.set(settingsProvider.serverAddress());
		userName.set(settingsProvider.userName());
		
		ConnectionManager.registerDataHandler(ConnectionSuccessInfo.class, new IDataHandler<ConnectionSuccessInfo>()
		{
			@Override
			public void handle(ConnectionSuccessInfo data)
			{
				isWaiting.set(false);
				status.set(ResourcesProvider.get(R.string.connection_success));
				
				if (_isHost)
				{
					status.set(ResourcesProvider.get(R.string.hosting_creating));
					isWaiting.set(true);
					ConnectionManager.send(new GameCreationRequest());
				}
				else
				{
					NavigationManager.navigateTo(GameListViewModel.class);
				}
			}
		});
		
		ConnectionManager.registerDataHandler(ConnectionFaultInfo.class, new IDataHandler<ConnectionFaultInfo>()
		{
			@Override
			public void handle(ConnectionFaultInfo data)
			{
				isWaiting.set(false);
				status.set(ResourcesProvider.get(R.string.connection_fault));
			}
		});
		
		ConnectionManager.registerDataHandler(GameCreationInfo.class, new IDataHandler<GameCreationInfo>()
		{
			@Override
			public void handle(GameCreationInfo data)
			{
				isWaiting.set(false);
				status.set(ResourcesProvider.get(R.string.hosting_created));
				NavigationManager.navigateTo(HostingViewModel.class);
			}
		});
	}
	
	private void connect()
	{
		status.set(ResourcesProvider.get(R.string.connection_connecting));
		isWaiting.set(true);
		ConnectionManager.connect();
	}
	
	public void disconnect()
	{
		ConnectionManager.disconnect();
	}
}
