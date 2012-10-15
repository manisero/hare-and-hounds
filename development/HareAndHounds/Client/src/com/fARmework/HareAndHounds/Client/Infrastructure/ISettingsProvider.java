package com.fARmework.HareAndHounds.Client.Infrastructure;

import android.content.Context;

public interface ISettingsProvider extends com.fARmework.core.client.Infrastructure.ISettingsProvider, com.fARmework.utils.Android.Infrastructure.ISettingsProvider, com.fARmework.modules.PositionTracking.Android.Infrastructure.ISettingsProvider
{
	void setContext(Context context);
	
	void setServerAddress(String serverAddress);
	void setPort(int port);
	
	String getUserName();
	void setUserName(String userName);
	
	int getCheckpointSoundMinPeriod();
	int getCheckpointSoundMaxPeriod();
}
