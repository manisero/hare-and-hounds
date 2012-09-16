package com.fARmework.modules.SpaceGraphics;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import android.opengl.*;

public class GraphicsRenderer implements GLSurfaceView.Renderer 
{
	@Override
	public void onDrawFrame(GL10 gl) 
	{
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		GLES20.glViewport(0, 0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
		GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	}
}
