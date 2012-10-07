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
	private final ISettingsProvider _settingsProvider;
	
	public StringObservable Status = new StringObservable();
	public BooleanObservable IsWaiting = new BooleanObservable(false);
	public BooleanObservable IsConnected = new BooleanObservable(false);
	
	public Command NewGame = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			if (!IsConnected.get())
			{
				ConnectionManager.registerDataHandler(ConnectionSuccessInfo.class, new IDataHandler<ConnectionSuccessInfo>()
				{
					@Override
					public void handle(ConnectionSuccessInfo data)
					{
						IsConnected.set(true);
						Status.set(ResourcesProvider.getString(R.string.connection_success));
						IsWaiting.set(false);
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
	
	public Command JoinGame = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			if (!IsConnected.get())
			{
				ConnectionManager.registerDataHandler(ConnectionSuccessInfo.class, new IDataHandler<ConnectionSuccessInfo>()
				{
					@Override
					public void handle(ConnectionSuccessInfo data)
					{
						IsConnected.set(true);
						Status.set(ResourcesProvider.getString(R.string.connection_success));
						IsWaiting.set(false);
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
	
	public Command Disconnect = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			disconnect();
		}
	};
	
	public Command Options = new Command()
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
				Status.set(ResourcesProvider.getString(R.string.connection_fault));
				IsWaiting.set(false);
			}
		});
	}
	
	private void connect()
	{
		Status.set(ResourcesProvider.getString(R.string.connection_connecting));
		IsWaiting.set(true);
		ConnectionManager.connect(_settingsProvider.getServerAddress());
	}
	
	public void disconnect()
	{
		ConnectionManager.disconnect();
		IsWaiting.set(false);
		
		if (IsConnected.get())
		{
			IsConnected.set(false);
			Status.set(ResourcesProvider.getString(R.string.connection_disconnected));
		}
	}
	
	@Override
	public void dispose()
	{
		disconnect();
	}
}
