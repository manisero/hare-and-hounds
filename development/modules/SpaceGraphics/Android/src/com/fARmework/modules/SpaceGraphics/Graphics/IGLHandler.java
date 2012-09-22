package com.fARmework.modules.SpaceGraphics.Graphics;

public interface IGLHandler
{
	void initialize();
	
	void setViewport(int width, int height);
	void setRotationMatrix(float[] rotationMatrix);
	
	void draw(Model model);
}
