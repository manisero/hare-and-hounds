package com.fARmework.Creativity.SoundIndicator;

import com.fARmework.Creativity.SoundIndicator._impl.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class SoundActivity extends Activity 
{
	private ISoundPlayer _soundPlayer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        
        _soundPlayer = new SoundPlayer(this, 2000);
        
        Button playButton = (Button) findViewById(R.id.playButton);
        
        playButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
		        _soundPlayer.play();	
			}
		});
        
        Button stopButton = (Button) findViewById(R.id.stopButton);
        
        stopButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_soundPlayer.stop();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_sound, menu);
        return true;
    }
}
