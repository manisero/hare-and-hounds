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
	
	private int _soundID;
	
	private Timer _timer;
	private TimerTask _playTask;
	
	private boolean _ready = false;
	private boolean _playing = false;
	
	@Override
	public void loadSound(Context context, int soundID, int period)
	{
		if (_playing)
		{
			stop();
		}
		
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
		
		_soundID = soundID;
	}
	
	@Override
	public void play(int period)
	{
		if (!_ready || _playing)
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
				
				_soundPool.play(_soundID, volumeRatio, volumeRatio, 1, 0, 1.0f);
				_playing = true;
			}
		};
		
		_timer.scheduleAtFixedRate(_playTask, 0, period);
	}

	@Override
	public void stop()
	{
		_playTask.cancel();
		_playing = false;
	}

	@Override
	public void setLoopPeriod(int period)
	{		
		stop();
		play(period);
	}
}
