package com.fARmework.modules.PositionTracking.Android._impl;

import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;
import com.fARmework.modules.PositionTracking.Android.IPositionService;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class PositionService implements IPositionService
{
	private ILocationManagerResolver _locationManagerResolver;
	
	private LocationManager _locationManager;
	private LocationManager getLocationManager()
	{
		if (_locationManager == null)
		{
			_locationManager = _locationManagerResolver.resolve();
		}
		
		return _locationManager;
	}
	
	public PositionService(ILocationManagerResolver locationManagerResolver)
	{
		_locationManagerResolver = locationManagerResolver;
	}
	
	@Override
	public void GetPosition(final IPositionListener positionListener)
	{
		getLocationManager().requestSingleUpdate(new Criteria(), new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				positionListener.onPosition(location.getLongitude(), location.getLatitude());
			}
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras)
			{
			}
			
			@Override
			public void onProviderEnabled(String provider)
			{
			}
			
			@Override
			public void onProviderDisabled(String provider)
			{
			}
		}, null);
	}
}
