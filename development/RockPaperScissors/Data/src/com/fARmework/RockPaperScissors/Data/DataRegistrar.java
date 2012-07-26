package com.fARmework.RockPaperScissors.Data;

import com.fARmework.core.data.IDataRegistry;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(CreateGameRequest.class);
		dataRegistry.register(GameListRequest.class);
		dataRegistry.register(JoinGameRequest.class);
	}
}
