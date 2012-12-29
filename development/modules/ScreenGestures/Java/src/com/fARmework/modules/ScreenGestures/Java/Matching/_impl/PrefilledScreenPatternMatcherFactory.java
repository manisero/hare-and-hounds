package com.fARmework.modules.ScreenGestures.Java.Matching._impl;

import com.fARmework.modules.ScreenGestures.Java.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.*;

public class PrefilledScreenPatternMatcherFactory extends EmptyScreenPatternMatcherFactory 
{
	public PrefilledScreenPatternMatcherFactory()
	{
		register(DiffusedScreenGesture.class, new DiffusedScreenPatternMatcher());
		register(DirectionalScreenGesture.class, new DirectionalScreenPatternMatcher());
		register(GroupedScreenGesture.class, new GroupedScreenPatternMatcher());
		register(PlainScreenGesture.class, new PlainScreenPatternMatcher());
	}
}
