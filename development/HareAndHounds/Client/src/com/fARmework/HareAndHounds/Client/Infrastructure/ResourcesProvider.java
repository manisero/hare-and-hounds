package com.fARmework.HareAndHounds.Client.Infrastructure;

import android.content.res.Resources;

public class ResourcesProvider
{
	private static Resources _resources;
	
	public static void setResources(Resources resources)
	{
		_resources = resources;
	}
	
	public static String getString(int resourceID)
	{
		return _resources.getString(resourceID);
	}
	
	public static int getInteger(int resourceID)
	{
		return _resources.getInteger(resourceID);
	}
}
