package com.fARmework.modules.SpaceGraphics;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import android.opengl.*;

public class GraphicsRenderer implements GLSurfaceView.Renderer 
{
    private Arrow _arrow;
    private GLHandler _glHandler;

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) 
    {
        _glHandler = new GLHandler();
        _arrow = new Arrow(_glHandler);
    }

    @Override
    public void onDrawFrame(GL10 unused) 
    {
    	_arrow.rotate(20.0f, 0.0f, 20.0f);
    	_arrow.draw();
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) 
    {
        _arrow.setViewport(width, height);
    }
}
