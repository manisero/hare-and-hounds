package com.fARmework.creat.GestureDetector.Processing;

import com.fARmework.creat.GestureDetector.*;

public interface IGestureProcessorFactory 
{
	boolean register(Class<? extends IGesture<?>> gesture, IGestureProcessor<?> processor);
	
	boolean unregister(Class<? extends IGesture<?>> gesture);
	
	@SuppressWarnings("rawtypes")
	IGestureProcessor<?> get(Class<? extends IGesture> gesture);
}
