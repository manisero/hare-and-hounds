package com.fARmework.utils.Android.Media;

import android.content.*;

public interface ISoundPoolManager
{
	public interface ISoundLoadListener
	{
		void onLoaded();
	}
	
	void setContext(Context context);
	
	int loadSound(int soundResourceID, ISoundLoadListener soundLoadListener);
	void play(int soundID);
}
