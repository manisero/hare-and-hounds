package com.fARmework.client.Logic.BackgroundTasks;

public interface IBackgroundTaskFactory
{
	IReadTask createReadTask(IProgressListener<String> messageListener);
}
