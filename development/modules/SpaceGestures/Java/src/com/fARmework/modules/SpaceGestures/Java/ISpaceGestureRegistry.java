package com.fARmework.modules.SpaceGestures.Java;

import java.util.*;

import com.fARmework.modules.SpaceGestures.Java.Gestures.*;

public interface ISpaceGestureRegistry
{
	List<SpaceGesture> getGestures();
	
	boolean register(SpaceGesture gesture);
	boolean unregister(SpaceGesture gesture);
}
