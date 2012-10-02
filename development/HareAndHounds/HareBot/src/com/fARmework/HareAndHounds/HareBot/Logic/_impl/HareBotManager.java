package com.fARmework.HareAndHounds.HareBot.Logic._impl;

import com.fARmework.HareAndHounds.HareBot.Infrastructure.*;
import com.fARmework.HareAndHounds.HareBot.Logic.*;
import com.fARmework.core.client.Connection.*;
import com.google.inject.*;

public class HareBotManager implements IHareBotManager
{
	private IConnectionManager _connectionManager;
	private ISettingsProvider _settingsProvider;
	
	@Inject
	public HareBotManager(IConnectionManager connectionManager, ISettingsProvider settingsProvider)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void runBot()
	{
		System.out.println("Hare bot ran");
	}
}
