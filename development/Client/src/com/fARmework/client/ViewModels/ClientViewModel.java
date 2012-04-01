package com.fARmework.client.ViewModels;

import java.io.IOException;
import java.net.Socket;

import com.fARmework.client.Logic.ReadTask;

import android.view.View;
import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

public class ClientViewModel
{
	private class ConnectCommand extends Command
	{
		@Override
		public void Invoke(View view, Object... params)
		{
			try
	    	{
	    		_socket = new Socket("192.168.0.106", 6666);
			}
	    	catch (IOException e)
			{
	    		message.set(e.getMessage());
	    		return;
			}
	    	
	    	new ReadTask().execute(_socket, message);
		}
	}
	
	private Socket _socket;
	
	public StringObservable message = new StringObservable();
	public ConnectCommand connect = new ConnectCommand();
	
	public void disconnect()
	{
		if (_socket == null)
			return;
		
    	try
    	{
    		_socket.close();
    	}
    	catch (IOException e)
	    { }
	}
}
