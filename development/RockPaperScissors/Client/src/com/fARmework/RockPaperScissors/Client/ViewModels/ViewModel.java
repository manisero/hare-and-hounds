package com.fARmework.RockPaperScissors.Client.ViewModels;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.core.client.Connection.IConnectionHandler;
import com.fARmework.core.client.Connection.IConnectionManager;

public abstract class ViewModel
{
	protected IConnectionManager ConnectionManager;
	protected IConnectionHandler ConnectionHandler;
	protected IActivitiesManager ActivitiesManager;
	
	protected ViewModel(IConnectionManager connectionManager, IConnectionHandler connectionHandler, IActivitiesManager activitiesManager)
	{
		ConnectionManager = connectionManager;
		ConnectionHandler = connectionHandler;
		ActivitiesManager = activitiesManager;
	}
}
