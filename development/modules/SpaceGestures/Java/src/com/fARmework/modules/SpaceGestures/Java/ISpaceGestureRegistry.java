package com.fARmework.modules.SpaceGestures.Java;

import java.util.*;

public interface ISpaceGestureRegistry
{
	List<SpaceGesture> getGestures();
	
	boolean register(SpaceGesture gesture);
	boolean unregister(SpaceGesture gesture);
}
