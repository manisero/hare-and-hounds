package com.fARmework.HareAndHounds.Client.ViewModels;

import android.os.*;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Android.*;
import com.fARmework.modules.PositionTracking.Android.IPositionService.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class HoundsViewModel extends ViewModel
{
	public static final String POSITION_UPDATE_INTERVAL_KEY = HoundsViewModel.class.getCanonicalName() + "POSITION_UPDATE_INTERVAL";
	
	private IPositionService _positionSerivce;
	
	@Inject
	public HoundsViewModel(IPositionService positionService, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_positionSerivce = positionService;
		
		ConnectionManager.registerDataHandler(CheckpointEnteredInfo.class, new IDataHandler<CheckpointEnteredInfo>()
		{
			@Override
			public void handle(CheckpointEnteredInfo data)
			{
				ConnectionManager.unregisterDataHandlers(CheckpointEnteredInfo.class);
				ContextManager.navigateTo(CheckpointViewModel.class);
			}
		});
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
