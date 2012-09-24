package com.fARmework.HareAndHounds.Client.ViewModels;

import android.os.*;

import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Android.*;
import com.fARmework.modules.PositionTracking.Android.IPositionService.IPositionListener;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class HareViewModel extends ViewModel
{
	public static final String POSITION_UPDATE_INTERVAL_KEY = HareViewModel.class.getCanonicalName() + "POSITION_UPDATE_INTERVAL";
	
	private IPositionService _positionSerivce;
	
	@Inject
	public HareViewModel(IPositionService positionService, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_positionSerivce = positionService;
	}
	
	@Override
	public void setData(Bundle data)
	{
		int positionUpdateInterval = data.getInt(POSITION_UPDATE_INTERVAL_KEY);
		
		_positionSerivce.startGettingPosition(positionUpdateInterval, new IPositionListener()
		{
			@Override
			public void onPosition(PositionData position)
			{
				ConnectionManager.send(position);
			}
		});
	}
}
