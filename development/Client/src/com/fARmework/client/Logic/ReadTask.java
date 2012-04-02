package com.fARmework.client.Logic;

import gueei.binding.observables.StringObservable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.AsyncTask;

public class ReadTask extends AsyncTask<ReadTask.Parameter, String, Boolean> implements IReadTask
{
	public class Parameter
	{
		private Socket _socket;
		private StringObservable _message;
		
		public Parameter(Socket socket, StringObservable message)
		{
			_socket = socket;
			_message = message;
		}
		
		public Socket getSocket()
		{
			return _socket;
		}
		
		public StringObservable getMessage()
		{
			return _message;
		}
	}

	private StringObservable _message;
	
	public void execute(Socket socket, StringObservable message)
	{
		execute(new Parameter(socket, message));
	}
	
	@Override
	protected Boolean doInBackground(Parameter... params)
	{
		try
        {
			_message = params[0].getMessage();
			BufferedReader reader = new BufferedReader(new InputStreamReader((params[0].getSocket()).getInputStream()));
            publishProgress("Connected");
			
			while (true)
			{
				publishProgress(reader.readLine());
			}
        }
	    catch (IOException e)
	    {
	    	publishProgress(e.getMessage());
	    	return false;
	    }
	}
	
	@Override
	protected void onProgressUpdate(String... messages)
	{
		_message.set(messages[0]);
	}
}
