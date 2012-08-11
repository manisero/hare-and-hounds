package com.fARmework.modules.ScreenGestures.Java.Processing;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;

public interface IGestureProcessorFactory 
{
	boolean register(Class<? extends Gesture<?>> gesture, IGestureProcessor<?> processor);
	
	boolean unregister(Class<? extends Gesture<?>> gesture);
	
	@SuppressWarnings("rawtypes")
	IGestureProcessor<?> get(Class<? extends Gesture> gesture);
}
