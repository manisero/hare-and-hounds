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
	
	private final IPositionService _positionSerivce;
	
	private int _positionUpdateInterval;
	
	@Inject
	public HoundsViewModel(IPositionService positionService, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		_positionSerivce = positionService;
	}

	@Override
	public void initialize(Bundle data)
	{
		_positionUpdateInterval = data.getInt(POSITION_UPDATE_INTERVAL_KEY);
	}
	
	@Override
	public void onEntering()
	{
		ConnectionManager.registerDataHandler(CheckpointEnteredInfo.class, new IDataHandler<CheckpointEnteredInfo>()
		{
			@Override
			public void handle(CheckpointEnteredInfo data)
			{
				Bundle bundle = new Bundle();
				bundle.putDouble(CheckpointViewModel.INITIAL_DIRECTION_KEY, data.NextCheckpointDirection);
				ContextManager.navigateTo(CheckpointViewModel.class, bundle);
			}
		});
		
		_positionSerivce.startGettingPosition(_positionUpdateInterval, new IPositionListener()
		{
			@Override
			public void onPosition(PositionData position)
			{
				ConnectionManager.send(position);
			}
		});
	}
	
	@Override
	public void onLeaving()
	{
		ConnectionManager.unregisterDataHandlers(CheckpointEnteredInfo.class);
		// _positionSerivce.stopGettingPosition(...) // TODO: implement
	}
}
