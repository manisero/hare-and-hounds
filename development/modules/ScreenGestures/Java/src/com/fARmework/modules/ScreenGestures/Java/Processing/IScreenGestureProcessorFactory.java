package com.fARmework.modules.ScreenGestures.Java.Processing;

import com.fARmework.modules.ScreenGestures.Java.Gestures.ScreenGesture;

public interface IScreenGestureProcessorFactory 
{
	<T> boolean register(Class<? extends ScreenGesture<T>> gesture, IScreenGestureProcessor<T> processor);
	boolean unregister(Class<? extends ScreenGesture<?>> gesture);
	
	<T> IScreenGestureProcessor<T> get(Class<? extends ScreenGesture<T>> gesture);
}
