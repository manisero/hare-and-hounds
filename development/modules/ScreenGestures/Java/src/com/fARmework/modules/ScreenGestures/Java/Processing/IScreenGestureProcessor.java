package com.fARmework.modules.ScreenGestures.Java.Processing;

import com.fARmework.modules.ScreenGestures.Data.*;

public interface IScreenGestureProcessor<T> 
{
	T[][] getGestureGrid(ScreenGestureData data, int gridSize);
}
