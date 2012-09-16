package com.fARmework.HareAndHounds.Client.ViewModels;

import android.view.*;

import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

import gueei.binding.*;
import gueei.binding.observables.*;

public class OptionsViewModel extends ViewModel
{
	private ISettingsProvider _settingsProvider;
	
	public StringObservable serverAddress = new StringObservable();
	public StringObservable userName = new StringObservable();
	
	public Command save = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_settingsProvider.setServerAddress(serverAddress.get());
			_settingsProvider.setUserName(userName.get());
		}
	};
	
	@Inject
	public OptionsViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		serverAddress.set(_settingsProvider.getServerAddress());
		userName.set(_settingsProvider.getUserName());
	}
}
