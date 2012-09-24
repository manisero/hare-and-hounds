package com.fARmework.HareAndHounds.Client.ViewModels;

import android.os.*;

import com.fARmework.HareAndHounds.Client.Logic.*;
import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class CheckpointViewModel extends ViewModel
{
	public static final String INITIAL_DIRECTION_KEY = CheckpointViewModel.class.getCanonicalName() + "INITIAL_DIRECTION";
	
	private IDirectionProvider _directionProvider;
	
	@Inject
	public CheckpointViewModel(IDirectionProvider directionProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		_directionProvider = directionProvider;
		
		ConnectionManager.registerDataHandler(CheckpointData.class, new IDataHandler<CheckpointData>()
		{
			@Override
			public void handle(CheckpointData data)
			{
				_directionProvider.setDirection((float)data.NextCheckpointDirection);
			}
		});
	}
	
	@Override
	public void setData(Bundle data)
	{
		_directionProvider.setDirection(data.getInt(INITIAL_DIRECTION_KEY));
	}
}
