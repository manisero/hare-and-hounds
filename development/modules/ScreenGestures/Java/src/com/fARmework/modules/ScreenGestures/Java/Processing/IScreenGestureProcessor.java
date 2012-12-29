package com.fARmework.modules.ScreenGestures.Java.Processing;

import com.fARmework.modules.ScreenGestures.Data.*;

public interface IScreenGestureProcessor 
{
	Boolean[][] getGestureGrid(ScreenGestureData data, int gridSize);
}
