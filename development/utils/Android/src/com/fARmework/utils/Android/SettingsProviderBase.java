package com.fARmework.utils.Android;

import android.content.Context;

public abstract class SettingsProviderBase
{
	private static String SHARED_PREFERENCES_NAME = "SETTINGS";
	
	private Context _context;
	
	// SharedPreferences access methods
	
	public void setContext(Context context)
	{
		_context = context;
	}
	
	protected String getString(String key, String defaultValue)
	{
		return _context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(key, defaultValue);
	}
	
	protected void setString(String key, String value)
	{
		_context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(key, value).commit();
	}
}
