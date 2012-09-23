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
	public static final String POSITION_UPDATE_INTERVAL_KEY = HareViewModel.class.getCanonicalName() + "OPPONENT_NAME";
	
	private IPositionService _positionSerivce;
	
	@Inject
	public HoundsViewModel(IPositionService positionService, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_positionSerivce = positionService;
		
		ConnectionManager.registerDataHandler(CheckpointData.class, new IDataHandler<CheckpointData>()
		{
			@Override
			public void handle(CheckpointData data)
			{
				ConnectionManager.unregisterDataHandlers(CheckpointData.class);
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
