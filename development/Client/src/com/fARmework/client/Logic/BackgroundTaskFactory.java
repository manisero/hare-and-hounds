package com.fARmework.client.Logic;

import com.google.inject.Inject;

public class BackgroundTaskFactory implements IBackgroundTaskFactory
{
	// Dependencies
	private IResourcesProvider _resourcesProvider;
	
	// Constructor
	@Inject
	public BackgroundTaskFactory(IResourcesProvider resourcesProvider)
	{
		_resourcesProvider = resourcesProvider;
	}
	
	// IBackgroundTaskFactory members:
	@Override
	public IReadTask createReadTask()
	{
		return new ReadTask(_resourcesProvider);
	}
}
