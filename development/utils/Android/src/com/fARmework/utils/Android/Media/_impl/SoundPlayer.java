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
	private TimerTask _playTask;
	
	private int _soundID;
	private boolean _loaded = false;
	private boolean _isPlaying = false;
	
	@Override
	public boolean isPlaying()
	{
		return _isPlaying;
	}
	
	@Override
	public void setPeriod(int period)
	{
		if (!_loaded || !_isPlaying)
		{
			return;
		}
		
		stop();
		play(period);
	}
	
	@Override
	public void load(Context context, int soundID)
	{
		load(context, soundID, null);
	}
	
	@Override
	public void load(Context context, int soundID, final ILoadListener loadListener)
	{
		if (_isPlaying)
		{
			stop();
		}
		
		_audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		
		_soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, SOURCE_QUALITY);
		
		_soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
			{
				_loaded = true;
				
				if (loadListener != null)
				{
					loadListener.onLoaded();
				}
			}
		});
		
		_soundID = _soundPool.load(context, soundID, 1);
	}
	
	@Override
	public void play(int period)
	{
		if (!_loaded || _isPlaying)
		{
			return;
		}
		
		Timer timer = new Timer(true);
		
		_playTask = new TimerTask()
		{
			@Override
			public void run()
			{				
				float currentVolume = _audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
				float maxVolume = _audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				
				float volumeRatio = currentVolume / maxVolume;
				
				_soundPool.play(_soundID, volumeRatio, volumeRatio, 1, 0, 1.0f);
			}
		};
		
		timer.scheduleAtFixedRate(_playTask, 0, period);
		_isPlaying = true;
	}

	@Override
	public void stop()
	{
		if (!_isPlaying)
		{
			return;
		}
		
		_playTask.cancel();
		_playTask = null;
		
		_isPlaying = false;
	}
}
