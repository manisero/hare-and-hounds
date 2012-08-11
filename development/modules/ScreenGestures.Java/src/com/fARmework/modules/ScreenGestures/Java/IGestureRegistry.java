package com.fARmework.modules.ScreenGestures.Java;

import java.util.*;

public interface IGestureRegistry 
{
	boolean add(IGesture<?> gesture);
	
	boolean delete(IGesture<?> gesture);
	
	List<IGesture<?>> getGestures();
}
