package com.fARmework.HareAndHounds.Client.ViewModels;

import android.os.*;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Android.Logic.*;
import com.fARmework.modules.PositionTracking.Android.Logic.IPositionProvider.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class HoundsViewModel extends ViewModel
{
	public static final String POSITION_UPDATE_INTERVAL_KEY = HoundsViewModel.class.getCanonicalName() + "POSITION_UPDATE_INTERVAL";
	
	private final IPositionProvider _positionProvider;
	
	private IPositionListener _positionListener;
	private int _positionUpdateInterval;
	
	@Inject
	public HoundsViewModel(IPositionProvider positionProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		_positionProvider = positionProvider;
		
		_positionListener = new IPositionListener()
		{
			@Override
			public void onPosition(PositionData position)
			{
				ConnectionManager.send(position);
			}
		};
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
		
		_positionProvider.startGettingPosition(_positionUpdateInterval, _positionListener);
	}
	
	@Override
	public void onLeaving()
	{
		ConnectionManager.unregisterDataHandlers(CheckpointEnteredInfo.class);
	}
	
	@Override
	public void dispose()
	{
		_positionProvider.stopGettingPosition(_positionListener);
	}
}
