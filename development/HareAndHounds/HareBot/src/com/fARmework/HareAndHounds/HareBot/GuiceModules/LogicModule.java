package com.fARmework.HareAndHounds.HareBot.GuiceModules;

import com.fARmework.HareAndHounds.HareBot.Logic.*;
import com.fARmework.HareAndHounds.HareBot.Logic._impl.*;
import com.google.inject.*;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IHareBotManager.class).to(HareBotManager.class);
	}
}
