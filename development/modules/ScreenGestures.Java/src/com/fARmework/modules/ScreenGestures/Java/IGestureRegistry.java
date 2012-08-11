package com.fARmework.modules.ScreenGestures.Java;

import java.util.*;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;

public interface IGestureRegistry 
{
	boolean add(Gesture<?> gesture);
	
	boolean delete(Gesture<?> gesture);
	
	List<Gesture<?>> getGestures();
}
