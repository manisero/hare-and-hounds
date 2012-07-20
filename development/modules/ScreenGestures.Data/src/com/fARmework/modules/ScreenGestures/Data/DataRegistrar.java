package com.fARmework.modules.ScreenGestures.Data;

import com.fARmework.core.data.IDataFactory;

public class DataRegistrar
{
	public void registerData(IDataFactory dataFactory)
	{
		dataFactory.register(GestureData.class);
	}
}
