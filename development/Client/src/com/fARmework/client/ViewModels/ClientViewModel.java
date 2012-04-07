package com.fARmework.client.ViewModels;

import java.io.IOException;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.client.Logic.IConnectionManager;
import com.fARmework.client.Logic.BackgroundTasks.IProgressListener;
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
			try
			{
				_connectionManager.connect(new IProgressListener<String>()
				{
					public void onUpdate(String value)
					{
						message.set(value);
					}
				});
			}
			catch (IOException e)
			{
				message.set(e.getMessage());
			}
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
