package com.fARmework.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.AsyncTask;
import android.widget.TextView;

public class ReadTask extends AsyncTask<ReadTask.Parameter, String, Boolean>
{
	public class Parameter
	{
		private Socket _socket;
		private TextView _view;
		
		public Parameter(Socket socket, TextView view)
		{
			_socket = socket;
			_view = view;
		}
		
		public Socket getSocket()
		{
			return _socket;
		}
		
		public TextView getView()
		{
			return _view;
		}
	}
	
	private TextView _view;
	
	public void execute(Socket socket, TextView view)
	{
		execute(new Parameter(socket, view));
	}
	
	@Override
	protected Boolean doInBackground(Parameter... params)
	{
		try
        {
			_view = params[0].getView();
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
		_view.setText(messages[0]);
	}
}
