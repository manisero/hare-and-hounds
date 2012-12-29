package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import com.fARmework.modules.ScreenGestures.Java.Gestures.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.GestureProcessors.*;

public class PrefilledScreenGestureProcessorFactory extends EmptyScreenGestureProcessorFactory 
{
	public PrefilledScreenGestureProcessorFactory()
	{
		register(DiffusedScreenGesture.class, new DiffusedScreenGestureProcessor());
		register(GroupedScreenGesture.class, new GroupedScreenGestureProcessor());
		register(PlainScreenGesture.class, new PlainScreenGestureProcessor());
	}
}
