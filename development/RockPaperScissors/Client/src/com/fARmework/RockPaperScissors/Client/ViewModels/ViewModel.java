package com.fARmework.RockPaperScissors.Client.ViewModels;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IActivitiesManager;
import com.fARmework.core.client.Connection.IConnectionManager;

public abstract class ViewModel
{
	protected IConnectionManager ConnectionManager;
	protected IActivitiesManager ActivitiesManager;
	
	protected ViewModel(IConnectionManager connectionManager, IActivitiesManager activitiesManager)
	{
		ConnectionManager = connectionManager;
		ActivitiesManager = activitiesManager;
	}
}
