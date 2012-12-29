package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.modules.ScreenGestures.Java.IScreenGestureRecognizer;
import com.fARmework.modules.ScreenGestures.Java.IScreenGestureRegistry;
import com.fARmework.modules.ScreenGestures.Java.Drawing.IGestureImageGenerator;
import com.fARmework.modules.ScreenGestures.Java.Drawing.IGestureImageViewer;
import com.fARmework.modules.ScreenGestures.Java.Drawing._impl.GestureImageGenerator;
import com.fARmework.modules.ScreenGestures.Java.Drawing._impl.GestureImageViewer;
import com.fARmework.modules.ScreenGestures.Java.Matching.IScreenPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Matching._impl.PrefilledScreenPatternMatcherFactory;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessor;
import com.fARmework.modules.ScreenGestures.Java.Processing._impl.ScreenGestureProcessor;
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
		bind(IScreenGestureProcessor.class).to(ScreenGestureProcessor.class).asEagerSingleton();
		bind(IScreenPatternMatcherFactory.class).to(PrefilledScreenPatternMatcherFactory.class).asEagerSingleton();
		bind(IGestureImageGenerator.class).to(GestureImageGenerator.class);
		bind(IGestureImageViewer.class).to(GestureImageViewer.class);
	}
}
