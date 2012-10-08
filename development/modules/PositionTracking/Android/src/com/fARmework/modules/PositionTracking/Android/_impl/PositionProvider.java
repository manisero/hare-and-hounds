package com.fARmework.modules.PositionTracking.Android._impl;

import java.util.*;

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
	
	private LocationManager _locationManager;
	private Map<IPositionListener, LocationListener> _listeners = new LinkedHashMap<IPositionListener, LocationListener>();
	
	@Inject
	public PositionProvider(ILocationManagerResolver locationManagerResolver)
	{
		_locationManagerResolver = locationManagerResolver;
	}
	
	private LocationManager getLocationManager()
	{
		if (_locationManager == null)
		{
			_locationManager = _locationManagerResolver.resolve();
		}
		
		return _locationManager;
	}
	
	@Override
	public void getSinglePosition(final IPositionListener positionListener)
	{
		String provider = getLocationManager().getBestProvider(new Criteria(), true);
		
		if (provider == null)
		{
			positionListener.onPosition(null);
			return;
		}
		
		getLocationManager().requestLocationUpdates(provider, 5000, 0, new LocationListener()
		{
			boolean _receivedFirstLocation = false; // first location update is ignored since it's always inaccurate
			
			@Override
			public void onLocationChanged(Location location)
			{
				if (_receivedFirstLocation)
				{
					positionListener.onPosition(new PositionData(location.getLatitude(), location.getLongitude()));
					getLocationManager().removeUpdates(this);
				}
				
				_receivedFirstLocation = true;
			}
			
			@Override
			public void onProviderDisabled(String provider)
			{
				positionListener.onPosition(null);
				getLocationManager().removeUpdates(this);
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
		String provider = getLocationManager().getBestProvider(new Criteria(), true);
		
		if (provider == null)
		{
			positionListener.onPosition(null);
			return;
		}
		
		LocationListener locationListener = new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				positionListener.onPosition(new PositionData(location.getLatitude(), location.getLongitude()));
			}
			
			@Override
			public void onProviderDisabled(String provider)
			{
				getLocationManager().removeUpdates(this);
			}
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras)
			{
			}

			@Override
			public void onProviderEnabled(String provider)
			{
			}
		};
		
		_listeners.put(positionListener, locationListener);
		
		getLocationManager().requestLocationUpdates(provider, updateInterval * 1000, 0, locationListener, null);
	}

	@Override
	public void stopGettingPosition(IPositionListener positionListener)
	{
		if (!_listeners.containsKey(positionListener))
		{
			return;
		}
		
		getLocationManager().removeUpdates(_listeners.get(positionListener));
	}
}
