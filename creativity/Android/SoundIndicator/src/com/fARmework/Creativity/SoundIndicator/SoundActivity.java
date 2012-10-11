package com.fARmework.Creativity.SoundIndicator;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class SoundActivity extends Activity {

	private SoundIndicator _soundIndicator;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        
        _soundIndicator = new SoundIndicator(this);
        
        Button playButton = (Button) findViewById(R.id.playButton);
        
        playButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
		        _soundIndicator.play();	
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
