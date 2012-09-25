package com.fARmework.modules.SpaceGraphics.Android.Graphics;

public interface IGLHandler
{
	void initialize();
	
	void setViewport(int width, int height);
	void setRotationMatrix(float[] rotationMatrix);
	void setDirection(float direction);
	
	void draw(Model model);
}
