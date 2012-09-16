package com.fARmework.HareAndHounds.Data.DataRegistrar;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.core.data.*;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(NewGameRequest.class);
		dataRegistry.register(NewGameResponse.class);
		
		dataRegistry.register(HarePositionData.class);
	}
}
