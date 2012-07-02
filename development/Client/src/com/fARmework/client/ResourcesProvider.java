package com.fARmework.client;

import android.content.res.Resources;

import com.fARmework.client.Infrastructure.IResourcesProvider;
import com.google.inject.Singleton;

@Singleton
public class ResourcesProvider implements IResourcesProvider
{
	public static Resources _resources;
	
	public void setResources(Resources resources)
	{
		_resources = resources;
	}
	
	@Override
	public String connected()
	{
		return _resources.getString(R.string.connected);
	}
}
