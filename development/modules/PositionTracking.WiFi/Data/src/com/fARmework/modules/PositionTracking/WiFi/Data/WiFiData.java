package com.fARmework.modules.PositionTracking.WiFi.Data;

import java.util.*;

public class WiFiData
{
	public Map<String, Integer> WiFiSignalStrengths = new LinkedHashMap<String, Integer>();
	
	public WiFiData()
	{
	}
	
	public WiFiData(Map<String, Integer> wifiSignalStrengths)
	{
		WiFiSignalStrengths.putAll(wifiSignalStrengths);
	}
	
	public void addSignalStrength(String accessPointMac, int signalStrength)
	{
		WiFiSignalStrengths.put(accessPointMac, signalStrength);
	}
}
