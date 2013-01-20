package com.fARmework.modules.PositionTracking.WiFi.Data.DataRegistrar;

import com.fARmework.core.data.*;
import com.fARmework.modules.PositionTracking.WiFi.Data.*;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(WiFiData.class);
	}
}
