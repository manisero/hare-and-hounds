package com.fARmework.HareAndHounds.Client.ViewModels;

import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class CheckpointViewModel extends ViewModel
{
	@Inject
	public CheckpointViewModel(IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
	}
}
