package com.fARmework.modules.PositionTracking.Android._impl;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;
import com.fARmework.modules.PositionTracking.Android.IPositionService;
import com.fARmework.modules.PositionTracking.Data.PositionData;
import com.google.inject.Inject;

public class PositionService implements IPositionService
{
	private ILocationManagerResolver _locationManagerResolver;
	
	@Inject
	public PositionService(ILocationManagerResolver locationManagerResolver)
	{
		_locationManagerResolver = locationManagerResolver;
	}
	
	@Override
	public void getSinglePosition(final IPositionListener positionListener)
	{
		final LocationManager manager = _locationManagerResolver.resolve();
		String provider = manager.getBestProvider(new Criteria(), true);
		
		if (provider == null)
		{
			positionListener.onPosition(null);
			return;
		}
		
		// Seems like requestSingleUpdate does not work properly...
		manager.requestLocationUpdates(provider, 3000, 5, new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				positionListener.onPosition(new PositionData(location.getLatitude(), location.getLongitude()));
				manager.removeUpdates(this);
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
				positionListener.onPosition(null);
				manager.removeUpdates(this);
			}
		}, null);
	}

	@Override
	public void startGettingPosition(final IPositionListener positionListener)
	{
		final LocationManager manager = _locationManagerResolver.resolve();
		String provider = manager.getBestProvider(new Criteria(), true);
		
		if (provider == null)
		{
			positionListener.onPosition(null);
			return;
		}
		
		manager.requestLocationUpdates(provider, 3000, 5, new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				positionListener.onPosition(new PositionData(location.getLatitude(), location.getLongitude()));
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
				positionListener.onPosition(null);
				manager.removeUpdates(this);
			}
		}, null);
	}

	@Override
	public void stopGettingPosition(IPositionListener positionListener)
	{
		// TODO: implement (maybe introduce state - Dictionary<IPositionListener, LocationListener>)
	}
}
