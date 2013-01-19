package com.fARmework.modules.PositionTracking.WiFi.Java.Logic;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Data.*;

public interface IPositionCalculator
{
	PositionData getPositionFromWiFi(WiFiData wifiData);
}
