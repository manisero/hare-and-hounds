package com.fARmework.client.Logic.BackgroundTasks;

public class BackgroundTaskFactory implements IBackgroundTaskFactory
{
	@Override
	public IReadTask createReadTask(IProgressListener<String> messageListener)
	{
		IReadTask readTask = new ReadTask();
		readTask.registerProgressListener(messageListener);
		
		return readTask;
	}
}
