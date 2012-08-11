package com.fARmework.RockPaperScissors.Data.DataRegistrar;

import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.core.data.IDataRegistry;

public class DataRegistrar
{
	public void registerData(IDataRegistry dataRegistry)
	{
		dataRegistry.register(GameCreationRequest.class);
		dataRegistry.register(GameCreationInfo.class);
		
		dataRegistry.register(GameListRequest.class);
		dataRegistry.register(GameListData.class);
		
		dataRegistry.register(GameJoinRequest.class);
		dataRegistry.register(GameJoinResponse.class);
		dataRegistry.register(GameStartInfo.class);
		
		dataRegistry.register(GestureInfo.class);
		dataRegistry.register(GameResultInfo.class);
		dataRegistry.register(NextGameInfo.class);
		dataRegistry.register(GameEndInfo.class);
	}
}
