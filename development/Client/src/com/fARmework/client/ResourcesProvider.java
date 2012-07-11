package com.fARmework.client;

import android.content.res.Resources;

public class ResourcesProvider
{
	private static Resources _resources;
	
	public void setResources(Resources resources)
	{
		_resources = resources;
	}
	
	public static String connectionConnecting()
	{
		return _resources.getString(R.string.connection_connecting);
	}
	
	public static String connectionSuccess()
	{
		return _resources.getString(R.string.connection_success);
	}
	
	public static String connectionFault()
	{
		return _resources.getString(R.string.connection_fault);
	}
}
