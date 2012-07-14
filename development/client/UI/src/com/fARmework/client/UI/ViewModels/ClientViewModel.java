package com.fARmework.client.UI.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.client.UI.R;
import com.fARmework.client.Connection.IConnectionManager;
import com.fARmework.client.Connection.IConnectionHandler;
import com.fARmework.client.UI.ResourcesProvider;
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
					ClientViewModel.this.message.set(message);
				}

				@Override
				public void onException(Throwable exception)
				{
					message.set(ResourcesProvider.get(R.string.connection_error));
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
