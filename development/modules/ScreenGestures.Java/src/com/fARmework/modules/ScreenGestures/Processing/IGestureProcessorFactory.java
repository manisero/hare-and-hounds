package com.fARmework.modules.ScreenGestures.Processing;

import com.fARmework.modules.ScreenGestures.*;

public interface IGestureProcessorFactory 
{
	boolean register(Class<? extends IGesture<?>> gesture, IGestureProcessor<?> processor);
	
	boolean unregister(Class<? extends IGesture<?>> gesture);
	
	@SuppressWarnings("rawtypes")
	IGestureProcessor<?> get(Class<? extends IGesture> gesture);
}
