package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class MainViewModel
{
	private IConnectionManager _connectionManager;
	
	public StringObservable message = new StringObservable();
	
	public Command connect = new Command()
	{
		@Override
		public void Invoke(View v, Object... args)
		{
			message.set(ResourcesProvider.get(R.string.connection_connecting));
			_connectionManager.connect();
		}
	};
	
	public Command sendGesture = new Command()
	{
		@Override
		public void Invoke(View v, Object... args)
		{
			_connectionManager.send(args[0]);
		}
	};
	
	@Inject
	public MainViewModel(IConnectionManager connectionManager)
	{
		_connectionManager = connectionManager;
		
		_connectionManager.registerDataHandler(String.class, new IDataHandler<String>()
		{
			@Override
			public void handle(String data)
			{
				MainViewModel.this.message.set(data);
			}
		});
	}
	
	public void disconnect()
	{
		_connectionManager.disconnect();
	}
}
