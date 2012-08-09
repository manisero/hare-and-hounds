package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;

public interface IGestureRecognizer 
{
	boolean isGesture(GestureData data);
	
	String getType();
}
