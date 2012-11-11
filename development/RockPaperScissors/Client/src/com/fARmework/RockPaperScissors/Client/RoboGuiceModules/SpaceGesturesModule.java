package com.fARmework.RockPaperScissors.Client.RoboGuiceModules;

import com.fARmework.modules.SpaceGestures.Android.*;
import com.fARmework.modules.SpaceGestures.Android._impl.*;
import com.google.inject.*;

public class SpaceGesturesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISensorManagerResolver.class).to(SensorManagerResolver.class);
		bind(ISpaceGestureRecorder.class).to(SpaceGestureRecorder.class);
	}
}
