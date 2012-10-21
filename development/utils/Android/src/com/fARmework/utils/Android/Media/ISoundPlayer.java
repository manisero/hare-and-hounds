package com.fARmework.utils.Android.Media;

import com.fARmework.utils.Android.Media.ISoundPoolManager.ISoundLoadListener;

public interface ISoundPlayer
{
	boolean isPlaying();
	void setPeriod(int period);
	
	void load(int soundResourceID);
	void load(int soundResourceID, ISoundLoadListener soundLoadListener);
	
	void play(int period);
	void stop();
}
