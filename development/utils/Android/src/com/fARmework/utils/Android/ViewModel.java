package com.fARmework.utils.Android;

import android.os.Bundle;
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
	
	public void leave()
	{
		ContextManager.finishCurrentActivity();
	}
	
	public void onLeaving()
	{
	}
}
