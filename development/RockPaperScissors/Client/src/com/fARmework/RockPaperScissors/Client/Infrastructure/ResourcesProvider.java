package com.fARmework.RockPaperScissors.Client.Infrastructure;

import android.content.res.Resources;

public class ResourcesProvider
{
	private static Resources _resources;
	
	public static void setResources(Resources resources)
	{
		_resources = resources;
	}
	
	public static String get(int resourceID)
	{
		return _resources.getString(resourceID);
	}
}
