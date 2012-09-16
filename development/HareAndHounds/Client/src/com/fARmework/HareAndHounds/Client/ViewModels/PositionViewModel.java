package com.fARmework.HareAndHounds.Client.ViewModels;

import android.view.View;

import com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider;
import com.fARmework.HareAndHounds.Data.NewGameRequest;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.modules.PositionTracking.Android.IPositionService;
import com.fARmework.modules.PositionTracking.Android.IPositionService.IPositionListener;
import com.fARmework.modules.PositionTracking.Data.PositionData;
import com.fARmework.utils.Android.*;
import com.google.inject.Inject;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

public class PositionViewModel extends ViewModel
{
	private IPositionService _positionService;
	private ISettingsProvider _settingsProvider;
	
	public StringObservable position = new StringObservable("position");
	
	public Command newGame = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_positionService.getPosition(new IPositionListener()
			{
				@Override
				public void onPosition(PositionData position)
				{
					if (position != null)
					{
						PositionViewModel.this.position.set(position.Longitude + ", " + position.Latitude);
						ConnectionManager.send(new NewGameRequest(_settingsProvider.getUserName(), position));
					}
					else
					{
						PositionViewModel.this.position.set("Could not read position.");
					}
				}
			});
		}
	};
	
	@Inject
	public PositionViewModel(IPositionService positionService, ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_positionService = positionService;
		_settingsProvider = settingsProvider;
		
		connect();
	}
	
	@Override
	public void onLeaving()
	{		
		disconnect();
	}
	
	private void connect()
	{
		ConnectionManager.connect(_settingsProvider.getServerAddress());
	}
	
	public void disconnect()
	{
		ConnectionManager.disconnect();
	}
}
