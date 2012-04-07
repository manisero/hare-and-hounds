package com.fARmework.client.Logic.BackgroundTasks;

public interface IProgressListener<T>
{
	void onUpdate(T value);
}
