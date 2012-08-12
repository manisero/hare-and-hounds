package com.fARmework.modules.ScreenGestures.Java.Processing;

import com.fARmework.modules.ScreenGestures.Java.Gestures.Gesture;

public interface IGestureProcessorFactory 
{
	<T> boolean register(Class<? extends Gesture<T>> gesture, IGestureProcessor<T> processor);
	boolean unregister(Class<? extends Gesture<?>> gesture);
	
	<T> IGestureProcessor<T> get(Class<? extends Gesture<T>> gesture);
}
