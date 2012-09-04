package com.fARmework.RockPaperScissors.Client.ViewModels;

import android.os.Bundle;

import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.core.client.Connection.IConnectionManager;

public abstract class ViewModel
{
	protected IConnectionManager ConnectionManager;
	protected IContextManager ContextManager;
	
	protected ViewModel(IConnectionManager connectionManager, IContextManager contextManager)
	{
		ConnectionManager = connectionManager;
		ContextManager = contextManager;
	}
	
	public void setData(Bundle data)
	{
	}
	
	public void onLeaving()
	{
		
	}
}
