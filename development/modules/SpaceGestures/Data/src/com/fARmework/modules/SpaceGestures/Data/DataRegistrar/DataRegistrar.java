package com.fARmework.modules.SpaceGestures.Data.DataRegistrar;

import com.fARmework.core.data.IDataRegistry;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(SpaceGestureData.class);
	}
}
