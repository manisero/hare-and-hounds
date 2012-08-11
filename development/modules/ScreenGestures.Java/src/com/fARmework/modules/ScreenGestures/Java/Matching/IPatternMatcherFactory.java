package com.fARmework.modules.ScreenGestures.Java.Matching;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;

public interface IPatternMatcherFactory 
{
	boolean register(Class<? extends Gesture<?>> gesture, IPatternMatcher<?> matcher);
	
	boolean unregister(Class<? extends Gesture<?>> gesture);
	
	@SuppressWarnings("rawtypes")
	IPatternMatcher<?> get(Class<? extends Gesture> gesture);	
}
