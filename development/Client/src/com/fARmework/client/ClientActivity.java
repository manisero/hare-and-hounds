package com.fARmework.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ClientActivity extends Activity
{
	private TextView _logText;
	private TextView _messageText;
	private Button _connectButton;
	private Button _readButton;
	
	private static Socket _socket;
	private static BufferedReader _reader;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _logText = (TextView)findViewById(R.id.log);
        _messageText = (TextView)findViewById(R.id.message);
        _connectButton = (Button)findViewById(R.id.connectButton);
        _readButton = (Button)findViewById(R.id.readButton);
        
        _logText.setText("Press connect...");
        
        _connectButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_logText.setText("Connect button pressed");
				connect();
			}
		});
        
        _readButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				_logText.setText("Read button pressed");
				read();
			}
		});
    }
    
    private void connect()
    {
    	try
        {
    		_logText.setText("Connecting...");
            _socket = new Socket("192.168.0.106", 6666);
            
            _logText.setText("Initializing reader...");
            _reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            
            _logText.setText("Connected");
	    }
	    catch (UnknownHostException e)
	    {
	    	_messageText.setText(e.getMessage());
	        e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	    	_messageText.setText(e.getMessage());
	    	e.printStackTrace();
	    }	
    }
    
    private void read()
    {
    	try
        {
	    	String message = null;
	        while (true)
	        {
	        	message = _reader.readLine();
	        	_messageText.setText(message);
	        	Log.i("message", message);
	        	
	        	if (message.equals("nara"))
	        		break;
	        }
    	
	        _socket.close();
        }
	    catch (UnknownHostException e)
	    {
	    	_logText.setText(e.getMessage());
	        e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	    	_logText.setText(e.getMessage());
	    	e.printStackTrace();
	    }
    }
    
    private void write()
    {
    	try
    	{
	    	OutputStream out = _socket.getOutputStream();   
	        PrintWriter output = new PrintWriter(out);
	        output.println("Hello Android!");
    	}
    	catch (IOException e)
	    {
	    	_logText.setText(e.getMessage());
	    	e.printStackTrace();
	    }
    }
}