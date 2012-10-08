package com.fARmework.modules.PositionTracking.Android._impl;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;
import com.fARmework.modules.PositionTracking.Android.IPositionProvider;
import com.fARmework.modules.PositionTracking.Data.PositionData;
import com.google.inject.Inject;

public class PositionProvider implements IPositionProvider
{
	private final ILocationManagerResolver _locationManagerResolver;
	
	@Inject
	public PositionProvider(ILocationManagerResolver locationManagerResolver)
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
		
		manager.requestLocationUpdates(provider, 5000, 0, new LocationListener()
		{
			boolean _receivedFirstLocation = false; // first location update is ignored since it's always inaccurate
			
			@Override
			public void onLocationChanged(Location location)
			{
				if (_receivedFirstLocation)
				{
					positionListener.onPosition(new PositionData(location.getLatitude(), location.getLongitude()));
					manager.removeUpdates(this);
				}
				
				_receivedFirstLocation = true;
			}
			
			@Override
			public void onProviderDisabled(String provider)
			{
				positionListener.onPosition(null);
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
		}, null);
	}

	@Override
	public void startGettingPosition(int updateInterval, final IPositionListener positionListener)
	{
		final LocationManager manager = _locationManagerResolver.resolve();
		String provider = manager.getBestProvider(new Criteria(), true);
		
		if (provider == null)
		{
			positionListener.onPosition(null);
			return;
		}
		
		manager.requestLocationUpdates(provider, updateInterval * 1000, 0, new LocationListener()
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
