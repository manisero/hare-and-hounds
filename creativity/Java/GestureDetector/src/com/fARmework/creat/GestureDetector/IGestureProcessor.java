package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;

public interface IGestureProcessor<T> 
{
	T[][] getGestureGrid(GestureData data, int gridSize);
}
