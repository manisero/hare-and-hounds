package com.fARmework.modules.PositionTracking.Android._impl;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;
import com.fARmework.modules.PositionTracking.Android.IPositionService;
import com.fARmework.modules.PositionTracking.Data.PositionData;

public class PositionService implements IPositionService
{
	private ILocationManagerResolver _locationManagerResolver;
	
	public PositionService(ILocationManagerResolver locationManagerResolver)
	{
		_locationManagerResolver = locationManagerResolver;
	}
	
	@Override
	public void GetPosition(final IPositionListener positionListener)
	{
		_locationManagerResolver.resolve().requestSingleUpdate(new Criteria(), new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				positionListener.onPosition(new PositionData(location.getLongitude(), location.getLatitude()));
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
