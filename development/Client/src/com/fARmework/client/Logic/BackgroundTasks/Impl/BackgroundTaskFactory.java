package com.fARmework.client.Logic.BackgroundTasks.Impl;

import com.fARmework.client.Logic.BackgroundTasks.IBackgroundTaskFactory;
import com.fARmework.client.Logic.BackgroundTasks.IProgressListener;
import com.fARmework.client.Logic.BackgroundTasks.IReadTask;

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
