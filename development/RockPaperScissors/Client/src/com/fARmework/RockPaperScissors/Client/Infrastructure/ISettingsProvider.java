package com.fARmework.RockPaperScissors.Client.Infrastructure;

import android.content.Context;

public interface ISettingsProvider extends com.fARmework.core.client.Infrastructure.ISettingsProvider
{
	void setContext(Context context);
	
	void setServerAddress(String serverAddress);
	
	String getUserName();
	void setUserName(String userName);
}
