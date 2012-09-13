package com.fARmework.modules.PositionTracking.Android._impl;

import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;

import android.content.Context;
import android.location.LocationManager;

public class LocationManagerResolver implements ILocationManagerResolver
{
	private Context _context;

	private LocationManager _locationManager;
	
	@Override
	public void setContext(Context context)
	{
		_context = context;
	}
	
	@Override
	public LocationManager resolve()
	{
		if (_locationManager == null)
		{
			_locationManager = (LocationManager)_context.getSystemService(Context.LOCATION_SERVICE);
		}
		
		return _locationManager;
	}
}
