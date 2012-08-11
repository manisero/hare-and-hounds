package com.fARmework.modules.ScreenGestures.Java.Processing;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;

public interface IGestureProcessorFactory 
{
	<T> boolean register(Class<? extends Gesture<T>> gesture, IGestureProcessor<T> processor);
	boolean unregister(Class<? extends Gesture<?>> gesture);
	
	@SuppressWarnings("rawtypes")
	IGestureProcessor<?> get(Class<? extends Gesture> gesture);
}
