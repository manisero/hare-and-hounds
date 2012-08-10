package com.fARmework.creat.GestureDetector;

import java.util.*;

public interface IGestureRegistry 
{
	boolean add(IGesture<?> gesture);
	
	boolean delete(IGesture<?> gesture);
	
	List<IGesture<?>> getGestures();
}
