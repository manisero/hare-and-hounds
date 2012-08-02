package com.fARmework.core.client.Connection.Impl;

import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Infrastructure.ISettingsProvider;
import com.fARmework.core.data.IDataService;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class NettyConnectionManagerProvider implements Provider<IConnectionManager>
{
	private ISettingsProvider _settingsProvider;
	private IDataService _dataService;
	
	private IConnectionManager _connectionManager;
	
	@Inject
	public NettyConnectionManagerProvider(ISettingsProvider settingsProvider, IDataService dataService)
	{
		_settingsProvider = settingsProvider;
		_dataService = dataService;
	}
	
	@Override
	public IConnectionManager get()
	{
		if (_connectionManager == null || _connectionManager.isDisposed())
		{
			_connectionManager = new NettyConnectionManager(_settingsProvider, _dataService);
		}
		
		return _connectionManager;
	}
}
