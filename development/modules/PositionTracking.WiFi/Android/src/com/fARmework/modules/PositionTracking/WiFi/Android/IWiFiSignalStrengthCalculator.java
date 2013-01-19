package com.fARmework.modules.PositionTracking.WiFi.Android;

public interface IWiFiSignalStrengthCalculator
{
	int calculateSignalStrength(int rssi, int maxStrength);
}
