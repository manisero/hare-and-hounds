package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.RockPaperScissors.Server.Logic.IGamesManager;
import com.fARmework.RockPaperScissors.Server.Logic.IGestureProcessor;
import com.fARmework.RockPaperScissors.Server.Logic.ISingleGameManagerFactory;
import com.fARmework.RockPaperScissors.Server.Logic._impl.GamesManager;
import com.fARmework.RockPaperScissors.Server.Logic._impl.GestureProcessor;
import com.fARmework.RockPaperScissors.Server.Logic._impl.SingleGameManagerFactory;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IGamesManager.class).to(GamesManager.class);
		bind(ISingleGameManagerFactory.class).to(SingleGameManagerFactory.class);
		bind(IGestureProcessor.class).to(GestureProcessor.class);
	}
}
