package com.fARmework.RockPaperScissors.Client.Infrastructure._impl;

import android.content.Context;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.google.inject.Singleton;

@Singleton
public class SettingsProvider implements ISettingsProvider
{
	private static String SHARED_PREFERENCES_NAME = "SETTINGS";
	
	private Context _context;
	
	// SharedPreferences access methods
	// NOTE: SharedPreferences access methods are highly inefficient. Probably some caching should be introduced.
	
	@Override
	public void setContext(Context context)
	{
		_context = context;
	}
	
	private String getString(String key, String defaultValue)
	{
		return _context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(key, defaultValue);
	}
	
	private void setString(String key, String value)
	{
		_context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(key, value).commit();
	}
	
	// ISettingsProvider members
	
	@Override
	public String getServerAddress()
	{
		return getString("server_address", ResourcesProvider.getString(R.string.defaultServerAddress));
	}

	@Override
	public void setServerAddress(String serverAddress)
	{
		setString("server_address", serverAddress);
	}
	
	@Override
	public int getPort()
	{
		return ResourcesProvider.getInteger(R.integer.port);
	}
	
	@Override
	public String getUserName()
	{
		return getString("user_name", ResourcesProvider.getString(R.string.defaultUserName));
	}
	
	@Override
	public void setUserName(String userName)
	{
		setString("user_name", userName);
	}
}
