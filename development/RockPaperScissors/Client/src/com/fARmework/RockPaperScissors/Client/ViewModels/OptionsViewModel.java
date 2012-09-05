package com.fARmework.RockPaperScissors.Client.ViewModels;

import java.util.Collection;

import gueei.binding.IObservable;
import gueei.binding.Observer;
import gueei.binding.observables.StringObservable;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.google.inject.Inject;

public class OptionsViewModel extends ViewModel
{
	public StringObservable serverAddress = new StringObservable();
	public StringObservable userName = new StringObservable();
	
	// NOTE: Is is recommended that Observers are declared as fields so that reference to them is kept
	private Observer _serverAddressObserver = new Observer()
	{
		@Override
		public void onPropertyChanged(IObservable<?> arg0, Collection<Object> arg1)
		{
			_settingsProvider.setServerAddress((String)arg0.get());
		}
	};
	
	private Observer _userNameObserver = new Observer()
	{
		@Override
		public void onPropertyChanged(IObservable<?> arg0, Collection<Object> arg1)
		{
			_settingsProvider.setUserName((String)arg0.get());
		}
	};
	
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public OptionsViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		serverAddress.set(_settingsProvider.getServerAddress());
		serverAddress.subscribe(_serverAddressObserver);
		
		userName.set(_settingsProvider.getUserName());
		userName.subscribe(_userNameObserver);
	}
}
