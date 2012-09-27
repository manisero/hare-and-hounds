package com.fARmework.modules.ScreenGestures.Java.Matching;

import com.fARmework.modules.ScreenGestures.Java.Gestures.ScreenGesture;

public interface IScreenPatternMatcherFactory 
{
	<T> boolean register(Class<? extends ScreenGesture<T>> gesture, IScreenPatternMatcher<T> matcher);
	boolean unregister(Class<? extends ScreenGesture<?>> gesture);
	
	<T> IScreenPatternMatcher<T> get(Class<? extends ScreenGesture<T>> gesture);	
}
