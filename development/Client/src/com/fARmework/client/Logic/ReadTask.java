package com.fARmework.client.Logic;

import gueei.binding.observables.StringObservable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.AsyncTask;

public class ReadTask extends AsyncTask<ReadTask.Parameter, ReadTask.Progress, Boolean> implements IReadTask
{
	// Inner classes
	public class Parameter
	{
		public Socket socket;
		public StringObservable output;
		
		private Parameter(Socket socket, StringObservable output)
		{
			this.socket = socket;
			this.output = output;
		}
	}
	
	public class Progress
	{
		public StringObservable output;
		public String value;
		
		private Progress(StringObservable target, String value)
		{
			this.output = target;
			this.value = value;
		}
	}
	
	// IReadTask members:
	public void execute(Socket socket, StringObservable output)
	{
		execute(new Parameter(socket, output));
	}
	
	// AsyncTask members:
	@Override
	protected Boolean doInBackground(Parameter... params)
	{
		Progress progress = new Progress(params[0].output, "");
		
		try
        {
			BufferedReader reader = new BufferedReader(new InputStreamReader((params[0].socket).getInputStream()));
			
			progress.value = "Connected";
            publishProgress(progress);
            
			while (true)
			{
				progress.value = reader.readLine();
				publishProgress(progress);
			}
        }
	    catch (IOException e)
	    {
	    	progress.value = e.getMessage();
	    	publishProgress(progress);
	    	return false;
	    }
	}
	
	@Override
	protected void onProgressUpdate(Progress... progress)
	{
		progress[0].output.set(progress[0].value);
	}
}
