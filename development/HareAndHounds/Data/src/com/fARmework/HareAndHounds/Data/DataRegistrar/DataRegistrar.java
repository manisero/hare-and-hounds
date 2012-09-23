package com.fARmework.HareAndHounds.Data.DataRegistrar;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.core.data.*;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(NewGameRequest.class);
		dataRegistry.register(NewGameResponse.class);
		
		dataRegistry.register(GameListRequest.class);
		dataRegistry.register(GameListResponse.class);
		
		dataRegistry.register(JoinGameRequest.class);
		dataRegistry.register(JoinGameResponse.class);
		
		dataRegistry.register(GameStartInfo.class);
		dataRegistry.register(CheckpointData.class);
	}
}
