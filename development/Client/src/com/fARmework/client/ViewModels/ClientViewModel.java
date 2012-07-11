package com.fARmework.client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.client.Connection.IConnectionManager;
import com.fARmework.client.Connection.IMessageHandler;
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
			_connectionManager.connect(new IMessageHandler<String>()
			{
				public void onMessage(String value)
				{
					message.set(value);
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
