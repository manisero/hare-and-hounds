package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import com.fARmework.HareAndHounds.Client.Logic.*;
import com.fARmework.HareAndHounds.Client.Logic._impl.*;
import com.google.inject.*;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IDirectionProvider.class).to(DirectionProvider.class).asEagerSingleton();
	}
}
