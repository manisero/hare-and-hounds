package com.fARmework.client.UI;

import android.content.res.Resources;

public class ResourcesProvider
{
	private static Resources _resources;
	
	public void setResources(Resources resources)
	{
		_resources = resources;
	}
	
	public static String get(int resourceID)
	{
		return _resources.getString(resourceID);
	}
}
