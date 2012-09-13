package com.fARmework.modules.PositionTracking.Android._impl;

import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;

import android.content.Context;
import android.location.LocationManager;

public class LocationManagerResolver implements ILocationManagerResolver
{
	private Context _context;
	
	@Override
	public void setContext(Context context)
	{
		_context = context;
	}
	
	@Override
	public LocationManager resolve()
	{
		return (LocationManager)_context.getSystemService(Context.LOCATION_SERVICE);
	}
}
