package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.Inject;

public class OptionsViewModel extends ViewModel
{
	private final ISettingsProvider _settingsProvider;
	
	public StringObservable ServerAddress = new StringObservable();
	public StringObservable UserName = new StringObservable();
	
	public Command Save = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_settingsProvider.setServerAddress(ServerAddress.get());
			_settingsProvider.setUserName(UserName.get());
		}
	};
	
	@Inject
	public OptionsViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_settingsProvider = settingsProvider;
		
		ServerAddress.set(_settingsProvider.getServerAddress());
		UserName.set(_settingsProvider.getUserName());
	}
}
