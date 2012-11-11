package com.fARmework.HareAndHounds.Client.ViewModels;

import gueei.binding.*;
import gueei.binding.observables.*;
import android.view.*;

import com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider;
import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.*;

public class OptionsViewModel extends ViewModel
{
	private final ISettingsProvider _settingsProvider;
	
	public StringObservable ServerAddress = new StringObservable();
	public StringObservable Port = new StringObservable();
	public StringObservable UserName = new StringObservable();
	public BooleanObservable PlayCheckpointSound = new BooleanObservable();
	
	public Command Save = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_settingsProvider.setServerAddress(ServerAddress.get());
			_settingsProvider.setPort(Integer.valueOf(Port.get()));
			_settingsProvider.setUserName(UserName.get());
			_settingsProvider.setPlayCheckpointSound(PlayCheckpointSound.get());
		}
	};
	
	@Inject
	public OptionsViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void onEntering()
	{
		ServerAddress.set(_settingsProvider.getServerAddress());
		Port.set(String.valueOf(_settingsProvider.getPort()));
		UserName.set(_settingsProvider.getUserName());
		PlayCheckpointSound.set(_settingsProvider.getPlayCheckpointSound());
	}
}
