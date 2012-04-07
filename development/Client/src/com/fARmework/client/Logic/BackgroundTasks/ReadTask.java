package com.fARmework.client.Logic.BackgroundTasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import android.os.AsyncTask;

public class ReadTask extends AsyncTask<Socket, String, Void> implements IReadTask
{
	private Collection<IProgressListener<String>> _progressListeners;
	
	public ReadTask()
	{
		_progressListeners = new ArrayList<IProgressListener<String>>();
	}
	
	public void registerProgressListener(IProgressListener<String> listener)
	{
		_progressListeners.add(listener);
	}
	
	public void execute(Socket socket)
	{
		super.execute(socket);
	}
	
	@Override
	protected Void doInBackground(Socket... params)
	{
		try
        {
			BufferedReader reader = new BufferedReader(new InputStreamReader((params[0]).getInputStream()));
            
			while (true)
			{
				publishProgress(reader.readLine());
			}
        }
	    catch (IOException e)
	    {
	    	publishProgress(e.getMessage());
	    	return null;
	    }
	}
	
	@Override
	protected void onProgressUpdate(String... values)
	{
		for (IProgressListener<String> listener : _progressListeners)
			listener.onUpdate(values[0]);
	}
}
