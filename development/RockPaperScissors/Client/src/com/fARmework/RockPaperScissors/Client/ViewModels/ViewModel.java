package com.fARmework.RockPaperScissors.Client.ViewModels;

import com.fARmework.RockPaperScissors.Client.Logic.IConnectionHandler;
import com.fARmework.core.client.Connection.IConnectionManager;

public abstract class ViewModel
{
	protected IConnectionManager ConnectionManager;
	protected IConnectionHandler ConnectionHandler;
	
	protected ViewModel(IConnectionManager connectionManager, IConnectionHandler connectionHandler)
	{
		ConnectionManager = connectionManager;
		ConnectionHandler = connectionHandler;
	}
}
