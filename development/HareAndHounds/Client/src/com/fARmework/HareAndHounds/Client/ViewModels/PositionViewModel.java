package com.fARmework.HareAndHounds.Client.ViewModels;

import gueei.binding.observables.StringObservable;

import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.modules.PositionTracking.Android.IPositionService;
import com.fARmework.modules.PositionTracking.Android.IPositionService.IPositionListener;
import com.fARmework.modules.PositionTracking.Data.PositionData;
import com.fARmework.utils.Android.IContextManager;
import com.fARmework.utils.Android.ViewModel;
import com.google.inject.Inject;

public class PositionViewModel extends ViewModel
{
	public StringObservable position = new StringObservable("position");
	
	private IPositionService _positionService;
	
	private boolean _readPosition = true;
	private IPositionListener _positionListener;
	
	@Inject
	public PositionViewModel(IPositionService positionService, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_positionService = positionService;
		
		_positionListener = new IPositionListener()
		{
			@Override
			public void onPosition(PositionData position)
			{
				if (position != null)
				{
					PositionViewModel.this.position.set(position.Longitude + ", " + position.Latitude);
					
					if (_readPosition)
					{
						_positionService.getPosition(_positionListener);
					}
				}
				else
				{
					PositionViewModel.this.position.set("Could not read position.");
				}
			}
		};
		
		_positionService.getPosition(_positionListener);
	}
	
	@Override
	public void onLeaving()
	{
		_readPosition = false;
	}
}
