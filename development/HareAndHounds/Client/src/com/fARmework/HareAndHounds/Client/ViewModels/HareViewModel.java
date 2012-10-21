package com.fARmework.HareAndHounds.Client.ViewModels;

import android.os.*;

import com.fARmework.HareAndHounds.Client.*;
import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Data.GameEndInfo.GameResult;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.PositionTracking.Android.Logic.*;
import com.fARmework.modules.PositionTracking.Android.Logic.IPositionProvider.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure.IContextManager.IDialogListener;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.*;

public class HareViewModel extends ViewModel
{
	public static final String POSITION_UPDATE_INTERVAL_KEY = HareViewModel.class.getCanonicalName() + "POSITION_UPDATE_INTERVAL";
	
	private final IPositionProvider _positionProvider;
	
	private IPositionListener _positionListener;
	private int _positionUpdateInterval;
	
	@Inject
	public HareViewModel(IPositionProvider positionProvider, IConnectionManager connectionManager, IContextManager contextManager)
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
		ConnectionManager.registerDataHandler(GameEndInfo.class, new IDataHandler<GameEndInfo>()
		{
			@Override
			public void handle(GameEndInfo data)
			{
				ContextManager.showDialogNotification(
					data.Result == GameResult.Victory ? ResourcesProvider.getString(R.string.hare_victory) : ResourcesProvider.getString(R.string.hare_defeat),
					ResourcesProvider.getString(R.string.dialog_confirm),
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							ContextManager.finishApplication();
						}
					});
			}
		});
		
		_positionProvider.startGettingPosition(_positionUpdateInterval, _positionListener);
	}
	
	@Override
	public void onLeaving()
	{
		ConnectionManager.unregisterDataHandlers(GameEndInfo.class);
	}
	
	@Override
	public void dispose()
	{
		_positionProvider.stopGettingPosition(_positionListener);
	}
}
