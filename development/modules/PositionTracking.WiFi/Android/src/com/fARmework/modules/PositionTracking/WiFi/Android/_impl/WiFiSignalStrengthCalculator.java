package com.fARmework.modules.PositionTracking.WiFi.Android._impl;

import java.lang.reflect.*;

import android.net.wifi.*;

import com.fARmework.modules.PositionTracking.WiFi.Android.*;

public class WiFiSignalStrengthCalculator implements IWiFiSignalStrengthCalculator
{
	private static final int DEFAULT_MIN_RSSI = -100;
	private static final int DEFAULT_MAX_RSSI = -55;
	
	private final int MIN_RSSI;
	private final int MAX_RSSI;
	
	public WiFiSignalStrengthCalculator()
	{
		int minRssi;
		int maxRssi;
		
		try
		{
			minRssi = getWiFiManagerInternalField("MIN_RSSI");
		} 
		catch (Exception ex)
		{
			minRssi = DEFAULT_MIN_RSSI;
		}
		
		try
		{
			maxRssi = getWiFiManagerInternalField("MAX_RSSI");
		}
		catch (Exception ex)
		{
			maxRssi = DEFAULT_MAX_RSSI;
		} 
		
		MIN_RSSI = minRssi;
		MAX_RSSI = maxRssi;
	}
	
	@Override
	public int calculateSignalStrength(int rssi, int maxStrength)
	{
		if (rssi <= MIN_RSSI)
		{
			return 0;
		}
		
		if (rssi >= MAX_RSSI)
		{
			return maxStrength;
		}
		
		double partitionSize = (MAX_RSSI - MIN_RSSI) / (double) (maxStrength);
		
		return (int) ((rssi - MIN_RSSI) / partitionSize);
	}
	
	private int getWiFiManagerInternalField(String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException 
	{
		Field field = WifiManager.class.getDeclaredField(fieldName);
		field.setAccessible(true);

		return field.getInt(null);
	}
}
