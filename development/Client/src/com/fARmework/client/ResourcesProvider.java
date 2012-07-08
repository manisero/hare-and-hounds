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
	public String connectionConnecting()
	{
		return _resources.getString(R.string.connection_connecting);
	}
	
	@Override
	public String connectionSuccess()
	{
		return _resources.getString(R.string.connection_success);
	}
	
	@Override
	public String connectionFault()
	{
		return _resources.getString(R.string.connection_fault);
	}
}
