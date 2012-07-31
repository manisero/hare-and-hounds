package com.fARmework.RockPaperScissors.Data;

import com.fARmework.core.data.IDataRegistry;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(CreateGameRequest.class);
		dataRegistry.register(CreateGameResponse.class);
		
		dataRegistry.register(GameListRequest.class);
		dataRegistry.register(GameListResponse.class);
		
		dataRegistry.register(JoinGameRequest.class);
		
		dataRegistry.register(GameStartInfo.class);
		dataRegistry.register(GestureData.class);
		
		dataRegistry.register(VictoryInfo.class);
		dataRegistry.register(DefeatInfo.class);
		dataRegistry.register(DrawInfo.class);
	}
}
