package com.fARmework.modules.ScreenGestures.Java.Matching;

import com.fARmework.modules.ScreenGestures.Java.*;

public interface IPatternMatcherFactory 
{
	boolean register(Class<? extends IGesture<?>> gesture, IPatternMatcher<?> matcher);
	
	boolean unregister(Class<? extends IGesture<?>> gesture);
	
	@SuppressWarnings("rawtypes")
	IPatternMatcher<?> get(Class<? extends IGesture> gesture);	
}
