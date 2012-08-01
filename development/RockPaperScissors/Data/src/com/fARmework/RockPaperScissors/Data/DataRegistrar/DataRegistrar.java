package com.fARmework.RockPaperScissors.Data.DataRegistrar;

import com.fARmework.RockPaperScissors.Data.CreateGameRequest;
import com.fARmework.RockPaperScissors.Data.CreateGameResponse;
import com.fARmework.RockPaperScissors.Data.DefeatInfo;
import com.fARmework.RockPaperScissors.Data.DrawInfo;
import com.fARmework.RockPaperScissors.Data.GameListRequest;
import com.fARmework.RockPaperScissors.Data.GameListResponse;
import com.fARmework.RockPaperScissors.Data.GameStartInfo;
import com.fARmework.RockPaperScissors.Data.GestureData;
import com.fARmework.RockPaperScissors.Data.JoinGameRequest;
import com.fARmework.RockPaperScissors.Data.VictoryInfo;
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
