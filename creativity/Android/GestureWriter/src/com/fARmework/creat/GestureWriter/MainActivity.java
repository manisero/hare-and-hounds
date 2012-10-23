package com.fARmework.creat.GestureWriter;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity 
{
	private MainView _mainView; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        _mainView = (MainView) findViewById(R.id.main_view);
        
        final Button saveButton = (Button) findViewById(R.id.save_button);
        final Button clearButton = (Button) findViewById(R.id.clear_button);
        
        saveButton.setOnClickListener(new OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				_mainView.save();
			}
		});
        
        clearButton.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) 
			{
				_mainView.clear();	
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
