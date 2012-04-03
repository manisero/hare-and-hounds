package com.fARmework.client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.client.Logic.IConnectionManager;
import com.google.inject.Inject;

public class ClientViewModel
{
	// Dependencies:
	private IConnectionManager _connectionManager;
	
	// Fields:
	public StringObservable message = new StringObservable();
	
	public Command connect = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_connectionManager.connect(message);
		}
	};
	
	// Constructor:
	@Inject
	public ClientViewModel(IConnectionManager connectionManager)
	{
		_connectionManager = connectionManager;
	}
	
	// Public methods:
	public void disconnect()
	{
		_connectionManager.disconnect();
	}
}
