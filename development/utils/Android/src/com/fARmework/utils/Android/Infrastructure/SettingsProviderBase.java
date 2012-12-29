package com.fARmework.utils.Android.Infrastructure;

import android.content.Context;

public abstract class SettingsProviderBase
{
	private static String SHARED_PREFERENCES_NAME = "SETTINGS";
	
	private Context _context;
	
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
	
	protected int getInt(String key, int defaultValue)
	{
		return _context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getInt(key, defaultValue);
	}
	
	protected void setInt(String key, int value)
	{
		_context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putInt(key, value).commit();
	}
	
	protected boolean getBool(String key, boolean defaultValue)
	{
		return _context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getBoolean(key, defaultValue);
	}
	
	protected void setBool(String key, boolean value)
	{
		_context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
	}
}
