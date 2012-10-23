package com.fARmework.utils.Android.Media._impl;

import android.content.*;
import android.media.*;
import android.media.SoundPool.*;

import com.fARmework.utils.Android.Media.*;

public class SoundPoolManager implements ISoundPoolManager
{
	private final static int MAX_STREAMS = 10;
	private final static int SOURCE_QUALITY = 0;
	private final static int PRIORITY = 1;
	
	private Context _context;
	
	private AudioManager _audioManager;
	private SoundPool _soundPool;
	
	private AudioManager getAudioManager()
	{
		if (_audioManager == null)
		{
			_audioManager = (AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
		}
		
		return _audioManager;
	}
	
	private SoundPool getSoundPool()
	{
		if (_soundPool == null)
		{
			_soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, SOURCE_QUALITY);
		}
		
		return _soundPool;
	}

	@Override
	public void setContext(Context context)
	{
		_context = context;
	}
	
	@Override
	public int loadSound(int soundResourceID, final ISoundLoadListener soundLoadListener)
	{
		getSoundPool().setOnLoadCompleteListener(new OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
			{
				soundLoadListener.onLoaded();
			}
		});
		
		return getSoundPool().load(_context, soundResourceID, PRIORITY);
	}

	@Override
	public void play(int soundID)
	{
		float currentVolume = getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volumeRatio = currentVolume / maxVolume;
		
		getSoundPool().play(soundID, volumeRatio, volumeRatio, PRIORITY, 0, 1.0f);
	}
}
