package com.fARmework.modules.ScreenGestures.Java.Drawing;

import java.awt.image.BufferedImage;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;

public interface IGestureImageGenerator
{
	public BufferedImage generateImage(ScreenGestureData gesture);
	public BufferedImage generateImage(ScreenGestureData gesture, int gridSize);
}
