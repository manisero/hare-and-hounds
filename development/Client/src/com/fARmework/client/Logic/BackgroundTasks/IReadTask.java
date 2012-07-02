package com.fARmework.client.Logic.BackgroundTasks;

import java.net.Socket;

public interface IReadTask
{
	public void registerProgressListener(IProgressListener<String> listener);
	public void execute(Socket socket);
}
