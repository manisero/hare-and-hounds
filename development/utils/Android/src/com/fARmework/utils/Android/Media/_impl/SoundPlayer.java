package com.fARmework.utils.Android.Media._impl;

import android.content.*;
import android.media.*;
import android.media.SoundPool.OnLoadCompleteListener;

import com.fARmework.utils.Android.Media.*;

import java.util.*;

public class SoundPlayer implements ISoundPlayer
{
	private int _delay;
	
	private AudioManager _audioManager;
	private SoundPool _soundPool;
	private int _id;
	
	private Timer _timer;
	private TimerTask _playTask;
	
	private boolean _ready = false;
	
	public SoundPlayer(Context context, int resourceID, int initialDelay)
	{
		_delay = initialDelay;
		
		_soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		_soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
			{
				_ready = true;
			}
		});
		
		_id = _soundPool.load(context, resourceID, 1);
		
		_audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	public void play()
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
		
		_timer.scheduleAtFixedRate(_playTask, 0, _delay);
	}

	@Override
	public void stop()
	{
		_playTask.cancel();
	}

	@Override
	public void setLoopDelay(int msDelay)
	{
		_delay = msDelay;
		
		stop();
		play();
	}
}
