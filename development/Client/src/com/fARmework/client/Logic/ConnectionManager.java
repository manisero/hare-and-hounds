package com.fARmework.client.Logic;

import gueei.binding.observables.StringObservable;

import java.io.IOException;
import java.net.Socket;

import com.google.inject.Inject;

public class ConnectionManager implements IConnectionManager
{
	// Dependencies:
	private ISocketCreator _socketCreator;
	private IBackgroundTaskFactory _backgroundTaskFactory;
	private IResourcesProvider _resourcesProvider;
	
	// Fields:
	private Socket _socket;
	
	// Constructor:
	@Inject
	public ConnectionManager(ISocketCreator socketCreator, IBackgroundTaskFactory backgroundTaskFactory, IResourcesProvider resourcesProvider)
	{
		_socketCreator = socketCreator;
		_backgroundTaskFactory = backgroundTaskFactory;
		_resourcesProvider = resourcesProvider;
	}
	
	// IConnectionManager members:
	@Override
	public void connect(StringObservable output)
	{
		try
		{
			_socket = _socketCreator.create(_resourcesProvider.serverAddress(), _resourcesProvider.port());
			_backgroundTaskFactory.createReadTask().execute(_socket, output);
		}
		catch (IOException e)
		{
			output.set(e.getMessage());
		}
	}

	@Override
	public void disconnect()
	{
		if (_socket == null)
			return;
		
    	try
    	{
    		_socket.close();
    	}
    	catch (IOException e)
	    { }
	}
}
