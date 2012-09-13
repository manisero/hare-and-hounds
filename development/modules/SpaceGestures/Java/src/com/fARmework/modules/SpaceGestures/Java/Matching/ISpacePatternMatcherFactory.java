package com.fARmework.modules.SpaceGestures.Java.Matching;

import com.fARmework.modules.SpaceGestures.Java.*;

public interface ISpacePatternMatcherFactory
{
	ISpacePatternMatcher get(Class<? extends SpaceGesture> gesture);
	
	boolean register(Class<? extends SpaceGesture> gesture, ISpacePatternMatcher matcher);
	boolean unregister(Class<? extends SpaceGesture> gesture);
}
