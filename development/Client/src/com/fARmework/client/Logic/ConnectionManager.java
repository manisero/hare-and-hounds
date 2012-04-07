package com.fARmework.client.Logic;

import java.io.IOException;
import java.net.Socket;

import com.fARmework.client.Logic.BackgroundTasks.IBackgroundTaskFactory;
import com.fARmework.client.Logic.BackgroundTasks.IProgressListener;
import com.google.inject.Inject;

public class ConnectionManager implements IConnectionManager
{
	private ISocketCreator _socketCreator;
	private IBackgroundTaskFactory _backgroundTaskFactory;
	private IResourcesProvider _resourcesProvider;
	
	private Socket _socket;
	
	@Inject
	public ConnectionManager(ISocketCreator socketCreator, IBackgroundTaskFactory backgroundTaskFactory, IResourcesProvider resourcesProvider)
	{
		_socketCreator = socketCreator;
		_backgroundTaskFactory = backgroundTaskFactory;
		_resourcesProvider = resourcesProvider;
	}
	
	@Override
	public void connect(IProgressListener<String> messageListener) throws IOException
	{
		_socket = _socketCreator.create(_resourcesProvider.serverAddress(), _resourcesProvider.port());
		_backgroundTaskFactory.createReadTask(messageListener).execute(_socket);
		
		messageListener.onUpdate(_resourcesProvider.connected());
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
