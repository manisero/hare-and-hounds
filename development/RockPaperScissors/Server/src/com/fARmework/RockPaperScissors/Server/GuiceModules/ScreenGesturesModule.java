package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.modules.ScreenGestures.Java.IGestureRecognizer;
import com.fARmework.modules.ScreenGestures.Java.IGestureRegistry;
import com.fARmework.modules.ScreenGestures.Java.Matching.IPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Matching._impl.PatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing.IGestureProcessorFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing._impl.GestureProcessorFactory;
import com.fARmework.modules.ScreenGestures.Java._impl.GestureRecognizer;
import com.fARmework.modules.ScreenGestures.Java._impl.GestureRegistry;
import com.google.inject.AbstractModule;

public class ScreenGesturesModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IGestureRecognizer.class).to(GestureRecognizer.class);
		bind(IGestureRegistry.class).to(GestureRegistry.class).asEagerSingleton();
		bind(IGestureProcessorFactory.class).to(GestureProcessorFactory.class).asEagerSingleton();
		bind(IPatternMatcherFactory.class).to(PatternMatcherFactory.class).asEagerSingleton();
	}
}
