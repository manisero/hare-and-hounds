package com.fARmework.RockPaperScissors.Server.Logic;

import com.fARmework.RockPaperScissors.Data.GameJoinRequest;

public interface ISingleGameManager
{
	void handleJoin(GameJoinRequest request);
}
