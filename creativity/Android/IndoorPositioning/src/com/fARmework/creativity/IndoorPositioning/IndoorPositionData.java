package com.fARmework.creativity.IndoorPositioning;

import java.util.LinkedHashMap;
import java.util.Map;

public class IndoorPositionData
{	
	public Map<String, Integer> WifiSignalData = new LinkedHashMap<String, Integer>();
	
	public void addWifiNetwork(String networkName, Integer level)
	{
		WifiSignalData.put(networkName, level);
	}
}
