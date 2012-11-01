package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.modules.ScreenGestures.Java.IScreenGestureRecognizer;
import com.fARmework.modules.ScreenGestures.Java.IScreenGestureRegistry;
import com.fARmework.modules.ScreenGestures.Java.Matching.IScreenPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Matching._impl.PrefilledScreenPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessorFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing._impl.PrefilledScreenGestureProcessorFactory;
import com.fARmework.modules.ScreenGestures.Java._impl.ScreenGestureRecognizer;
import com.fARmework.modules.ScreenGestures.Java._impl.ScreenGestureRegistry;
import com.google.inject.AbstractModule;

public class ScreenGesturesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IScreenGestureRecognizer.class).to(ScreenGestureRecognizer.class);
		bind(IScreenGestureRegistry.class).to(ScreenGestureRegistry.class).asEagerSingleton();
		bind(IScreenGestureProcessorFactory.class).to(PrefilledScreenGestureProcessorFactory.class).asEagerSingleton();
		bind(IScreenPatternMatcherFactory.class).to(PrefilledScreenPatternMatcherFactory.class).asEagerSingleton();
	}
}
