package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.RockPaperScissors.Server.Logic.IGameManager;
import com.fARmework.RockPaperScissors.Server.Logic.Impl.GameManager;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IGameManager.class).to(GameManager.class);
	}
}
