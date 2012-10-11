package com.fARmework.utils.Android.Media;

import android.content.*;

public interface ISoundPlayer
{
	void loadSound(Context context, int soundID);
	
	void play(int initialDelay);
	void play(Context context, int soundID, int initialDelay);
	
	void stop();
	
	void setLoopDelay(int delay);
}
