package com.fARmework.client.Logic;

import java.io.IOException;

import com.fARmework.client.Logic.BackgroundTasks.IProgressListener;

public interface IConnectionManager
{
	void connect(IProgressListener<String> messageListener) throws IOException;
	void disconnect();
}
