package com.fARmework.modules.SpaceGestures.Java.Matching;

import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public interface ISpacePatternMatcherFactory
{
	boolean register(Class<? extends SpaceGesture> gestureClass, ISpacePatternMatcher matcher);
	boolean unregister(Class<? extends SpaceGesture> gestureClass);
	
	ISpacePatternMatcher get(Class<? extends SpaceGesture> gestureClass);
}
