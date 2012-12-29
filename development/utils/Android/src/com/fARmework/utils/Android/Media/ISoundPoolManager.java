package com.fARmework.utils.Android.Media;

public interface ISoundPoolManager
{
	public interface ISoundLoadListener
	{
		void onLoaded();
	}
	
	int loadSound(int soundResourceID, ISoundLoadListener soundLoadListener);
	void play(int soundID);
}
