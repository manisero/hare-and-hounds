package com.fARmework.modules.PositionTracking.Android.Logic._impl;

import com.fARmework.modules.PositionTracking.Android.Logic.*;
import com.google.inject.*;

import android.content.Context;
import android.location.LocationManager;

public class LocationManagerResolver implements ILocationManagerResolver
{
	private final Context _context;

	private LocationManager _locationManager;
	
	@Inject
	public LocationManagerResolver(Context context)
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
