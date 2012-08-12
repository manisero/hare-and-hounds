package com.fARmework.modules.SpaceGestures.Java.Matching;

import com.fARmework.modules.SpaceGestures.Java.*;

public interface ISpaceGestureMatcherFactory
{
	ISpaceGestureMatcher get(Class<? extends SpaceGesture> gesture);
	
	boolean register(Class<? extends SpaceGesture> gesture, ISpaceGestureMatcher matcher);
	boolean unregister(Class<? extends SpaceGesture> gesture);
}
