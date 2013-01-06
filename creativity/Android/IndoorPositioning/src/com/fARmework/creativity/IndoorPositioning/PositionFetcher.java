package com.fARmework.creativity.IndoorPositioning;

import java.util.List;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class PositionFetcher
{
	private WifiManager _wifiManager;
	
	public PositionFetcher(WifiManager wifiManager)
	{
		_wifiManager = wifiManager;
	}
	
	public IndoorPositionData getCurrentPosition()
	{
		IndoorPositionData positionData = new IndoorPositionData();
		
		boolean wifiState = _wifiManager.isWifiEnabled();
		
		_wifiManager.setWifiEnabled(true);
		
		_wifiManager.startScan();
		
		List<ScanResult> scanResults = _wifiManager.getScanResults();
		
		if (scanResults != null)
		{
			for (ScanResult scanResult : scanResults)
			{
				positionData.addWifiNetwork(scanResult.BSSID, scanResult.level);
			}
		}
		
		_wifiManager.setWifiEnabled(wifiState);
		
		return positionData;
	}
}
