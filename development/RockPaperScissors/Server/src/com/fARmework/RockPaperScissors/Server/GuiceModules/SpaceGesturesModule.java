package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;
import com.fARmework.modules.SpaceGestures.Java.Matching._impl.*;
import com.fARmework.modules.SpaceGestures.Java.Processing.*;
import com.fARmework.modules.SpaceGestures.Java.Processing._impl.*;
import com.fARmework.modules.SpaceGestures.Java.Utilities.*;
import com.fARmework.modules.SpaceGestures.Java.Utilities._impl.*;
import com.fARmework.modules.SpaceGestures.Java._impl.*;
import com.google.inject.*;

public class SpaceGesturesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISpaceGestureRecognizer.class).to(SpaceGestureRecognizer.class);
		bind(ISpaceGestureRegistry.class).to(SpaceGestureRegistry.class).asEagerSingleton();
		bind(ISpacePatternMatcherFactory.class).to(SpacePatternMatcherFactory.class).asEagerSingleton();
		bind(ISpaceGestureDirectionRecognizer.class).to(SpaceGestureDirectionRecognizer.class);
		bind(ISpaceGestureFilter.class).to(SpaceGestureFilter.class);
		bind(ISpaceGestureProcessor.class).to(SpaceGestureProcessor.class);
		bind(ISpaceGestureSegmentator.class).to(SpaceGestureSegmentator.class);
		bind(INetAccelerationForceCalculator.class).to(NetAccelerationForceCalculator.class);
	}
}
