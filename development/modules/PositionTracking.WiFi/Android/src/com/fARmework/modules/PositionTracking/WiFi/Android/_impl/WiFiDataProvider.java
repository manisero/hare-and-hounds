package com.fARmework.modules.PositionTracking.WiFi.Android._impl;

import java.util.*;

import android.net.wifi.*;

import com.fARmework.modules.PositionTracking.WiFi.Android.*;
import com.fARmework.modules.PositionTracking.WiFi.Data.*;
import com.google.inject.*;

public class WiFiDataProvider implements IWiFiDataProvider
{
	private static final int MAX_WIFI_STRENGTH = 100;
	
	private final IWiFiManagerResolver _wifiManagerResolver;
	private final IWiFiSignalStrengthCalculator _wifiSignalStrengthCalculator;
	
	private WifiManager _wifiManager;
	
	@Inject
	public WiFiDataProvider(IWiFiManagerResolver wifiManagerResolver, IWiFiSignalStrengthCalculator wifiSignalStrengthCalculator)
	{
		_wifiManagerResolver = wifiManagerResolver;
		_wifiSignalStrengthCalculator = wifiSignalStrengthCalculator;
	}
	
	@Override
	public WiFiData getWiFiData()
	{
		getWifiManager().startScan();
		List<ScanResult> scanResults = getWifiManager().getScanResults();
		
		if (scanResults != null)
		{
			WiFiData wifiData = new WiFiData();
			
			for (ScanResult scanResult : scanResults)
			{
				int signalStrength = _wifiSignalStrengthCalculator.calculateSignalStrength(scanResult.level, MAX_WIFI_STRENGTH);
				
				wifiData.addSignalStrength(scanResult.SSID, signalStrength);
			}
			
			return wifiData;
		}
		else
		{
			return null;
		}
	}
	
	private WifiManager getWifiManager()
	{
		if (_wifiManager == null)
		{
			_wifiManager = _wifiManagerResolver.resolve();
		}
		
		return _wifiManager;
	}
}
