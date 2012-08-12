package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import com.fARmework.modules.SpaceGestures.Java.Matching._impl.*;
import com.fARmework.modules.SpaceGestures.Java._impl.*;
import com.google.inject.*;

public class SpaceGesturesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISpaceGestureRecognizer.class).to(SpaceGestureRecognizer.class);
		bind(ISpaceGestureRegistry.class).to(SpaceGestureRegistry.class).asEagerSingleton();
		bind(ISpaceGestureMatcherFactory.class).to(SpaceGestureMatcherFactory.class).asEagerSingleton();
	}
}
