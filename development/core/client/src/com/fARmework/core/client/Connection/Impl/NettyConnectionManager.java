package com.fARmework.core.client.Connection.Impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.fARmework.core.client.Connection.Impl.NettyConnectionTask.IConnectionListener;
import com.fARmework.core.client.Infrastructure.ISettingsProvider;
import com.fARmework.core.data.IDataService;
import com.google.inject.Inject;

public class NettyConnectionManager implements IConnectionManager, IConnectionListener
{
	private ISettingsProvider _settingsProvider;
	private IDataService _dataService;
	
	@SuppressWarnings("rawtypes")
	private Map<Class<?>, IDataHandler> _dataHandlers = new LinkedHashMap<Class<?>, IDataHandler>();
	private Channel _channel;
	
	@Inject
	public NettyConnectionManager(ISettingsProvider settingsProvider, IDataService dataService)
	{
		_settingsProvider = settingsProvider;
		_dataService = dataService;
	}
	
	@Override
	public void connect()
	{
		connect(_settingsProvider.serverAddress());
	}
	
	@Override
	public void connect(String serverAddress)
	{
		disconnect();
		new NettyConnectionTask(this, _dataService).connect(serverAddress, _settingsProvider.port());
	}
	
	@Override
	public void onConnected(Channel channel)
	{
		_channel = channel;
	}
	
	@Override
	public void disconnect()
	{
		if (_channel != null && _channel.isOpen())
		{
			_channel.close();
			_channel = null;
		}
	}
	
	@Override
	public <T> void registerDataHandler(Class<T> dataClass, IDataHandler<T> handler)
	{
		_dataHandlers.put(dataClass, handler);
	}
	
	@Override
	public void clearDataHandlers()
	{
		_dataHandlers.clear();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onDataReceived(Object data)
	{
		if (_dataHandlers.containsKey(data.getClass()))
		{
			_dataHandlers.get(data.getClass()).handle(data);
		}
	}
	
	@Override
	public void send(Object data)
	{
		if (_channel != null)
		{
			_channel.write(_dataService.toSerializedMessage(data));
		}
	}
}
