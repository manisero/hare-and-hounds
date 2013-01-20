package com.fARmework.modules.PositionTracking.WiFi.Android._impl;

import android.content.*;
import android.net.wifi.*;

import com.fARmework.modules.PositionTracking.WiFi.Android.*;
import com.google.inject.*;

public class WiFiManagerResolver implements IWiFiManagerResolver
{
	private final Context _context;
	
	@Inject
	public WiFiManagerResolver(Context context)
	{
		_context = context;
	}

	@Override
	public WifiManager resolve()
	{
		return (WifiManager)_context.getSystemService(Context.WIFI_SERVICE);
	}
}
