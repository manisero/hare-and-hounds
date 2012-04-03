package com.fARmework.client.Logic;

public class BackgroundTaskFactory implements IBackgroundTaskFactory
{
	// IBackgroundTaskFactory members:
	@Override
	public IReadTask createReadTask()
	{
		return new ReadTask();
	}
}
