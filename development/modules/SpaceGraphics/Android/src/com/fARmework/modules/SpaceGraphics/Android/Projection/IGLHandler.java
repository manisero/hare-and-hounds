package com.fARmework.modules.SpaceGraphics.Android.Projection;

import com.fARmework.modules.SpaceGraphics.Android.Models.*;

import javax.microedition.khronos.opengles.*;

public interface IGLHandler
{
	void initialize(GL10 gl);
	
	void setViewport(GL10 gl, int width, int height);
	void setRotationMatrix(float[] rotationMatrix);
	void setDirection(float direction);
	
	void draw(GL10 gl, Model model);
}
