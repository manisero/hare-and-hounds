package com.fARmework.modules.SpaceGraphics.Graphics;

public interface IGLHandler
{
	void setViewport(int width, int height);
	
	void draw(Model model);
}