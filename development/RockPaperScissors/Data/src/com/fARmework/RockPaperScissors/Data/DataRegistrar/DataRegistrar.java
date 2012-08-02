package com.fARmework.RockPaperScissors.Data.DataRegistrar;

import com.fARmework.RockPaperScissors.Data.GameCreationRequest;
import com.fARmework.RockPaperScissors.Data.GameCreationInfo;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListData;
import com.fARmework.RockPaperScissors.Data.GameResultInfo;
import com.fARmework.RockPaperScissors.Data.GameStartInfo;
import com.fARmework.RockPaperScissors.Data.GestureData;
import com.fARmework.RockPaperScissors.Data.GameJoinRequest;
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
		
		dataRegistry.register(GameStartInfo.class);
		
		dataRegistry.register(GestureData.class);
		dataRegistry.register(GameResultInfo.class);
	}
}
