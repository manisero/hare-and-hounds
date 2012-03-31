package com.fARmework.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ClientActivity extends Activity
{
	private TextView _logText;
	private TextView _messageText;
	private Button _connectButton;
	
	private static Socket _socket;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _logText = (TextView)findViewById(R.id.log);
        _messageText = (TextView)findViewById(R.id.message);
        _connectButton = (Button)findViewById(R.id.connectButton);
        
        _logText.setText("Press connect...");
        
        _connectButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				connect();
			}
		});
    }
    
    private void connect()
    {
    	try
    	{
    		_socket = new Socket("192.168.0.106", 6666);
		}
    	catch (IOException e)
		{ }
    	
    	new ReadTask().execute(_socket, _messageText);
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
    
    @Override
    public void onBackPressed()
    {
    	try
    	{
    		if (_socket != null)
    			_socket.close();
    	}
    	catch (IOException e)
	    {
	    	_logText.setText(e.getMessage());
	    	e.printStackTrace();
	    }
    	
    	super.onBackPressed();
    }
}