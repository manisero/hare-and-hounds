package com.fARmework.SpaceGestures.Java.GesturesAnalyzer.GuiceModules;

import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Analyzer.IGesturesAnalyzer;
import com.fARmework.SpaceGestures.Java.GesturesAnalyzer.Analyzer._impl.GesturesAnalyzer;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureDirectionRecognizer;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureFilter;
import com.fARmework.modules.SpaceGestures.Java.Processing.ISpaceGestureSegmentator;
import com.fARmework.modules.SpaceGestures.Java.Processing._impl.SpaceGestureDirectionRecognizer;
import com.fARmework.modules.SpaceGestures.Java.Processing._impl.SpaceGestureFilter;
import com.fARmework.modules.SpaceGestures.Java.Processing._impl.SpaceGestureSegmentator;
import com.fARmework.modules.SpaceGestures.Java.Utilities.INetAccelerationForceCalculator;
import com.fARmework.modules.SpaceGestures.Java.Utilities._impl.NetAccelerationForceCalculator;
import com.google.inject.AbstractModule;

public class LogicModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IGesturesAnalyzer.class).to(GesturesAnalyzer.class);
		
		bind(ISpaceGestureSegmentator.class).to(SpaceGestureSegmentator.class);
		bind(ISpaceGestureFilter.class).to(SpaceGestureFilter.class);
		bind(ISpaceGestureDirectionRecognizer.class).to(SpaceGestureDirectionRecognizer.class);
		bind(INetAccelerationForceCalculator.class).to(NetAccelerationForceCalculator.class);
	}
}
