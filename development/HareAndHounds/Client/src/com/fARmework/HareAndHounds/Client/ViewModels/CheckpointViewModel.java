package com.fARmework.HareAndHounds.Client.ViewModels;

import android.os.*;

import com.fARmework.HareAndHounds.Client.Logic.*;
import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class CheckpointViewModel extends ViewModel
{
	public static final String INITIAL_DIRECTION_KEY = CheckpointViewModel.class.getCanonicalName() + "INITIAL_DIRECTION";
	
	private final IDirectionProvider _directionProvider;
	
	private Arrow _arrowModel = new Arrow();
	
	@Inject
	public CheckpointViewModel(IDirectionProvider directionProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_directionProvider = directionProvider;
		
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
				ContextManager.navigateTo(HoundsViewModel.class);
			}
		});
	}
	
	@Override
	public void initialize(Bundle data)
	{
		_directionProvider.setDirection(data.getInt(INITIAL_DIRECTION_KEY));
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
