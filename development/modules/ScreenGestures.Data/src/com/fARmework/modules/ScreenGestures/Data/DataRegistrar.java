package com.fARmework.modules.ScreenGestures.Data;

import com.fARmework.core.data.IDataRegistry;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(GestureData.class);
	}
}
