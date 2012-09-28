package com.fARmework.modules.PositionTracking.Data.DataRegistrar;

import com.fARmework.core.data.IDataRegistry;
import com.fARmework.modules.PositionTracking.Data.PositionData;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(PositionData.class);
	}
}
