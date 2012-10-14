package com.fARmework.utils.Android.Media;

import android.content.*;

public interface ISoundPlayer
{
	public interface ILoadListener
	{
		void onLoaded();
	}
	
	boolean isPlaying();
	void setPeriod(int period);
	
	void load(Context context, int soundID);
	void load(Context context, int soundID, ILoadListener loadListener);
	
	void play(int period);
	void stop();
}
