package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.google.inject.Inject;

public class OptionsViewModel extends ViewModel
{
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
	
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public OptionsViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		serverAddress.set(_settingsProvider.getServerAddress());
		userName.set(_settingsProvider.getUserName());
	}
}
