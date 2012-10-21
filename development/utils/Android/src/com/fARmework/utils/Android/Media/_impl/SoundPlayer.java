package com.fARmework.utils.Android.Media._impl;

import java.util.*;

import com.fARmework.utils.Android.Media.*;
import com.fARmework.utils.Android.Media.ISoundPoolManager.ISoundLoadListener;
import com.google.inject.*;

public class SoundPlayer implements ISoundPlayer
{
	private final ISoundPoolManager _soundPoolManager;
	
	private int _soundID;
	private TimerTask _playTask;
	
	private boolean _loaded = false;
	private boolean _isPlaying = false;
	
	@Inject
	public SoundPlayer(ISoundPoolManager soundPoolManager)
	{
		_soundPoolManager = soundPoolManager;
	}
	
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
	public void load(int soundResourceID)
	{
		load(soundResourceID, null);
	}
	
	@Override
	public void load(int soundResourceID, final ISoundLoadListener soundLoadListener)
	{
		if (_isPlaying)
		{
			stop();
		}
		
		_soundID = _soundPoolManager.loadSound(soundResourceID, new ISoundLoadListener()
		{
			@Override
			public void onLoaded()
			{
				_loaded = true;
				
				if (soundLoadListener != null)
				{
					soundLoadListener.onLoaded();
				}
			}
		});
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
				_soundPoolManager.play(_soundID);
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
