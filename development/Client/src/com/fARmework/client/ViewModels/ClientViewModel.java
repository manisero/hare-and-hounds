package com.fARmework.client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.client.ResourcesProvider;
import com.fARmework.client.Connection.IConnectionManager;
import com.fARmework.client.Connection.IConnectionHandler;
import com.google.inject.Inject;

public class ClientViewModel
{
	private IConnectionManager _connectionManager;
	
	public StringObservable message = new StringObservable();
	
	public Command connect = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_connectionManager.connect(new IConnectionHandler()
			{
				@Override
				public void onConnectionSuccess()
				{
					message.set(ResourcesProvider.connectionSuccess());
				}

				@Override
				public void onConnectionFault()
				{
					message.set(ResourcesProvider.connectionFault());
				}
				
				@Override
				public void onMessage(String message)
				{
					ClientViewModel.this.message.set(message);
				}

				@Override
				public void onException(Throwable exception)
				{
					message.set(exception.getMessage());
				}
			});
		}
	};
	
	@Inject
	public ClientViewModel(IConnectionManager connectionManager)
	{
		_connectionManager = connectionManager;
	}
	
	public void disconnect()
	{
		_connectionManager.disconnect();
	}
}
