package com.fARmework.HareAndHounds.Client.ViewModels;

import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.*;
import com.google.inject.*;

public class HareViewModel extends ViewModel
{
	@Inject
	public HareViewModel(IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
	}
}
