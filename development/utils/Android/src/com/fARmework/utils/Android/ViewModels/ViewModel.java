package com.fARmework.utils.Android.ViewModels;

import android.os.Bundle;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.utils.Android.Infrastructure.*;

public abstract class ViewModel
{
	protected IConnectionManager ConnectionManager;
	protected IContextManager ContextManager;
	
	protected ViewModel(IConnectionManager connectionManager, IContextManager contextManager)
	{
		ConnectionManager = connectionManager;
		ContextManager = contextManager;
	}
	
	public void initialize(Bundle data)
	{
	}
	
	public void onEntering()
	{
	}
	
	public void onLeaving()
	{
	}
	
	protected void leave()
	{
		ContextManager.finishCurrentActivity();
	}
	
	public void dispose()
	{
	}
}
