package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.ResourcesProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IConnectionHandler;
import com.fARmework.core.client.Connection.Message;
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
			
			_connectionManager.connect(new IConnectionHandler()
			{
				@Override
				public void onConnectionSuccess()
				{
					message.set(ResourcesProvider.get(R.string.connection_success));
				}

				@Override
				public void onConnectionFault()
				{
					message.set(ResourcesProvider.get(R.string.connection_fault));
				}
				
				@Override
				public void onMessage(String message)
				{
					MainViewModel.this.message.set(message);
				}

				@Override
				public void onException(Throwable exception)
				{
					message.set(ResourcesProvider.get(R.string.connection_error));
				}
			});
		}
	};
	
	public Command sendGesture = new Command()
	{
		@Override
		public void Invoke(View v, Object... args)
		{
			_connectionManager.send(new Message(args[0]));
		}
	};
	
	@Inject
	public MainViewModel(IConnectionManager connectionManager)
	{
		_connectionManager = connectionManager;
	}
	
	public void disconnect()
	{
		_connectionManager.disconnect();
	}
}
