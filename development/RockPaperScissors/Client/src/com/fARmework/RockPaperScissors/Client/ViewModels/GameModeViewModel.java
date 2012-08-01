package com.fARmework.RockPaperScissors.Client.ViewModels;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.fARmework.core.client.Data.ConnectionFaultInfo;
import com.fARmework.core.client.Data.ConnectionSuccessInfo;
import com.google.inject.Inject;

import android.view.View;
import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

public class GameModeViewModel extends ViewModel
{
	public StringObservable status = new StringObservable();
	
	public Command connect = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set(ResourcesProvider.get(R.string.connection_connecting));
			ConnectionManager.connect();
		}
	};
	
	public Command create = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			NavigationManager.navigateTo(HostingViewModel.class);
		}
	};
	
	public Command join = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			NavigationManager.navigateTo(GameListViewModel.class);
		}
	};
	
	@Inject
	public GameModeViewModel(IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
		ConnectionManager.registerDataHandler(ConnectionSuccessInfo.class, new IDataHandler<ConnectionSuccessInfo>()
		{
			@Override
			public void handle(ConnectionSuccessInfo data)
			{
				status.set(ResourcesProvider.get(R.string.connection_success));
			}
		});
		
		ConnectionManager.registerDataHandler(ConnectionFaultInfo.class, new IDataHandler<ConnectionFaultInfo>()
		{
			@Override
			public void handle(ConnectionFaultInfo data)
			{
				status.set(ResourcesProvider.get(R.string.connection_fault));
			}
		});
	}
	
	public void disconnect()
	{
		ConnectionManager.disconnect();
	}
}
