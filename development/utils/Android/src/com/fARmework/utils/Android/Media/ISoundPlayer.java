package com.fARmework.utils.Android.Media;

import android.content.*;

public interface ISoundPlayer
{
	void loadSound(Context context, int soundID, int period);
	
	void play(int period);
	void stop();
	
	void setLoopPeriod(int period);
}
