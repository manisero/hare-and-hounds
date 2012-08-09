package com.fARmework.RockPaperScissors.Client.ViewModels;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.core.client.Connection.IConnectionManager;

public abstract class ViewModel
{
	protected IConnectionManager ConnectionManager;
	protected INavigationManager NavigationManager;
	
	protected ViewModel(IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		ConnectionManager = connectionManager;
		NavigationManager = navigationManager;
	}
	
	public void setData(Bundle data)
	{
	}
}
