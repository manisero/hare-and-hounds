package com.fARmework.utils.Android.Media._impl;

import android.content.*;
import android.media.*;
import android.media.SoundPool.OnLoadCompleteListener;

import com.fARmework.utils.Android.Media.*;

import java.util.*;

public class SoundPlayer implements ISoundPlayer
{
	private final static int MAX_STREAMS = 10;
	private final static int SOURCE_QUALITY = 0;
	
	private AudioManager _audioManager;
	private SoundPool _soundPool;
	private int _id;
	
	private Timer _timer;
	private TimerTask _playTask;
	
	private boolean _ready = false;
	
	@Override
	public void loadSound(Context context, int soundID)
	{
		_audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		
		_soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, SOURCE_QUALITY);
		_soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
			{
				_ready = true;
			}
		});
		
		_id = soundID;
	}
	
	@Override
	public void play(Context context, int soundID, int initialDelay)
	{
		loadSound(context, soundID);
		
		play(initialDelay);
	}	

	@Override
	public void play(int initialDelay)
	{
		if (!_ready)
		{
			return;
		}
		
		_timer = new Timer(true);
		
		_playTask = new TimerTask()
		{
			@Override
			public void run()
			{				
				float currentVolume = _audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				float maxVolume = _audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				
				float volumeRatio = currentVolume / maxVolume;
				
				_soundPool.play(_id, volumeRatio, volumeRatio, 1, 0, 1.0f);
			}
		};
		
		_timer.scheduleAtFixedRate(_playTask, 0, initialDelay);
	}

	@Override
	public void stop()
	{
		_playTask.cancel();
	}

	@Override
	public void setLoopDelay(int delay)
	{		
		stop();
		play(delay);
	}
}
