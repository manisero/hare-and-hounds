package com.fARmework.modules.ScreenGestures.Matching;

import com.fARmework.modules.ScreenGestures.*;

public interface IPatternMatcherFactory 
{
	boolean register(Class<? extends IGesture<?>> gesture, IPatternMatcher<?> matcher);
	
	boolean unregister(Class<? extends IGesture<?>> gesture);
	
	@SuppressWarnings("rawtypes")
	IPatternMatcher<?> get(Class<? extends IGesture> gesture);	
}
