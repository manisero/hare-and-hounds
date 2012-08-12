package com.fARmework.modules.ScreenGestures.Java.Processing;

import com.fARmework.modules.ScreenGestures.Data.*;

public interface IGestureProcessor<T> 
{
	T[][] getGestureGrid(GestureData data, int gridSize);
}
