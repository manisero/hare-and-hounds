package com.fARmework.Creativity.SoundIndicator;

import android.content.*;
import android.media.*;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.*;

public class SoundIndicator implements ILoopPlayer
{
	private int _delay = 2000;
	private SoundPool _soundPool;
	private boolean _ready = false;
	private boolean _playing = false;
	private int _id;
	private AudioManager _audioManager;
	
	public SoundIndicator(Context context)
	{
		_soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		_soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
			{
				_ready = true;
			}
		});
		
		_id = _soundPool.load(context, R.raw.beep, 1);
		
		_audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	public void play()
	{
		if (!_ready)
		{
			return;
		}
		
		_playing = true;
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while(_playing)
				{
					float currentVolume = _audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
					float maxVolume = _audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
					
					float volumeRatio = currentVolume / maxVolume;
					
					_soundPool.play(_id, volumeRatio, volumeRatio, 1, 0, 1f);
					
					Log.e("SOUND", "Sound played");
					
					try
					{
						Thread.sleep(_delay);
					} 
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void stop()
	{
		_playing = false;
	}

	@Override
	public void setRate(double rate)
	{	
		
	}
}
