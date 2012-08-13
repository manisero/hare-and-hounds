package com.fARmework.modules.ScreenGestures.Data.DataRegistrar;

import com.fARmework.core.data.IDataRegistry;
import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(ScreenGestureData.class);
	}
}
