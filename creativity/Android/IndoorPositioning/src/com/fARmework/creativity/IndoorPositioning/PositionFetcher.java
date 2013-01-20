package com.fARmework.creativity.IndoorPositioning;

import java.lang.reflect.*;

import android.net.wifi.*;

public class PositionFetcher
{
	private static final int DEFAULT_MIN_RSSI = -100;
	private static final int DEFAULT_MAX_RSSI = -55;
	
	private final int MIN_RSSI;
	private final int MAX_RSSI;
	
	public PositionFetcher()
	{
		int minRSSI, maxRSSI;
		
		try
		{
			minRSSI = getWiFiManagerField("MIN_RSSI");
		} 
		catch (Exception ex)
		{
			minRSSI = DEFAULT_MIN_RSSI;
		} 
		
		try
		{
			maxRSSI = getWiFiManagerField("MAX_RSSI");
		}
		catch (Exception ex)
		{
			maxRSSI = DEFAULT_MAX_RSSI;
		} 
		
		MIN_RSSI = minRSSI;
		MAX_RSSI = maxRSSI;
	}
	
	private int getWiFiManagerField(String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException 
	{
		Field field = WifiManager.class.getDeclaredField(fieldName);
		field.setAccessible(true);

		return field.getInt(null);
	}
	
	public int calculateSignalLevel(int rssi, int numLevels)
	{
		if (rssi <= MIN_RSSI)
		{
			return 0;
		}
		
		if (rssi >= MAX_RSSI)
		{
			return numLevels - 1;
		}
		
		double partitionSize = (MAX_RSSI - MIN_RSSI) / (double) (numLevels - 1);
		
		return (int) ((rssi - MIN_RSSI) / partitionSize);
	}
}
