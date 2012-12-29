package com.fARmework.modules.ScreenGestures.Java.Drawing;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;

public interface IGestureImageViewer
{
	public void showGesture(ScreenGestureData gesture);
	public void showGesture(ScreenGestureData gesture, int gridSize);
}
