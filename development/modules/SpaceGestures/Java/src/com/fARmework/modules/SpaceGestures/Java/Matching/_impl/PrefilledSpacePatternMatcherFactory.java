package com.fARmework.modules.SpaceGestures.Java.Matching._impl;

import com.fARmework.modules.SpaceGestures.Java.Gestures.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.*;

public class PrefilledSpacePatternMatcherFactory extends EmptySpacePatternMatcherFactory
{
	public PrefilledSpacePatternMatcherFactory()
	{
		register(CyclicSpaceGesture.class, new CyclicSpacePatternMatcher());
		register(PlainSpaceGesture.class, new PlainSpacePatternMatcher());
		register(SurfaceSpaceGesture.class, new SurfaceSpacePatternMatcher());
	}
}
