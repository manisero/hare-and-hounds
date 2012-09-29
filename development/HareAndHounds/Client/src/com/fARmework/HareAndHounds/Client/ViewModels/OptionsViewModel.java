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
	
	public StringObservable ServerAddress = new StringObservable();
	public StringObservable Port = new StringObservable();
	public StringObservable UserName = new StringObservable();
	
	public Command Save = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_settingsProvider.setServerAddress(ServerAddress.get());
			_settingsProvider.setPort(Integer.valueOf(Port.get()));
			_settingsProvider.setUserName(UserName.get());
		}
	};
	
	@Inject
	public OptionsViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		ServerAddress.set(_settingsProvider.getServerAddress());
		Port.set(String.valueOf(_settingsProvider.getPort()));
		UserName.set(_settingsProvider.getUserName());
	}
}
