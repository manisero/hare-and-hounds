package com.fARmework.modules.ScreenGestures.Java;

import java.util.*;

import com.fARmework.modules.ScreenGestures.Java.Gestures.ScreenGesture;

public interface IScreenGestureRegistry 
{
	boolean register(ScreenGesture<?> gesture);
	boolean unregister(ScreenGesture<?> gesture);
	
	List<ScreenGesture<?>> getGestures();
}
