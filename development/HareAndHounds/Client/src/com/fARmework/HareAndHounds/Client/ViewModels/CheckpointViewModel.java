package com.fARmework.HareAndHounds.Client.ViewModels;

import android.os.*;

import com.fARmework.HareAndHounds.Client.*;
import com.fARmework.HareAndHounds.Client.Logic.*;
import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Media.*;
import com.fARmework.utils.Android.Media.ISoundPlayer.ILoadListener;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.*;

public class CheckpointViewModel extends ViewModel
{
	public static final String INITIAL_DIRECTION_KEY = CheckpointViewModel.class.getCanonicalName() + "INITIAL_DIRECTION";
	
	private final IDirectionProvider _directionProvider;
	private final ISoundPlayer _soundPlayer;
	
	private Arrow _arrowModel = new Arrow();
	
	@Inject
	public CheckpointViewModel(IDirectionProvider directionProvider, ISoundPlayer soundPlayer, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		_directionProvider = directionProvider;
		_soundPlayer = soundPlayer;
	}
	
	@Override
	public void initialize(Bundle data)
	{
		_directionProvider.setDirection((float)data.getDouble(INITIAL_DIRECTION_KEY));
	}
	
	@Override
	public void onEntering()
	{
		ConnectionManager.registerDataHandler(CheckpointUpdateInfo.class, new IDataHandler<CheckpointUpdateInfo>()
		{
			@Override
			public void handle(CheckpointUpdateInfo data)
			{
				_directionProvider.setDirection((float)data.NextCheckpointDirection);
				_arrowModel.setColorRate((float)data.Accuracy);
			}
		});
		
		ConnectionManager.registerDataHandler(CheckpointLeftInfo.class, new IDataHandler<CheckpointLeftInfo>()
		{
			@Override
			public void handle(CheckpointLeftInfo data)
			{
				ContextManager.finishCurrentView();
			}
		});
		
		_soundPlayer.load(ContextManager.getCurrentContext(), R.raw.beep, new ILoadListener()
		{
			@Override
			public void onLoaded()
			{
				_soundPlayer.play(1000);
			}
		});
	}
	
	@Override
	public void onLeaving()
	{
		ConnectionManager.unregisterDataHandlers(CheckpointUpdateInfo.class);
		ConnectionManager.unregisterDataHandlers(CheckpointLeftInfo.class);
		_soundPlayer.stop();
	}
	
	public Model getArrowModel()
	{
		return _arrowModel;
	}
	
	public IDirectionProvider getDirectionProvider()
	{
		return _directionProvider;
	}
}
