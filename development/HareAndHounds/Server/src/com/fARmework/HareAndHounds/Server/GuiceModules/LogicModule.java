package com.fARmework.HareAndHounds.Server.GuiceModules;

import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.HareAndHounds.Server.Logic._impl.*;
import com.google.inject.*;

public class LogicModule extends AbstractModule 
{
	@Override
	protected void configure() 
	{
		bind(IGamesManager.class).to(GamesManager.class);
	}
}
