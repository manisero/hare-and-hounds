package com.fARmework.modules.ScreenGestures.Java;

import java.util.*;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;

public interface IGestureRegistry 
{
	boolean register(Gesture<?> gesture);
	boolean unregister(Gesture<?> gesture);
	
	List<Gesture<?>> getGestures();
}
