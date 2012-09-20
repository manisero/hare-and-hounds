package com.fARmework.modules.SpaceGraphics.Graphics;

import android.opengl.*;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import com.fARmework.modules.SpaceGraphics.Graphics.IOrientationProvider.IOrientationListener;
import com.fARmework.modules.SpaceGraphics.Graphics.Models.*;


public class GraphicsRenderer implements GLSurfaceView.Renderer
{
	private IOrientationProvider _orientationProvider;
	
    private Model _model;
    private GLHandler _glHandler;
    
    public GraphicsRenderer(IOrientationProvider orientationProvider)
    {
		_orientationProvider = orientationProvider;
    }
    
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) 
    {
        _glHandler = new GLHandler();
        _model = new Arrow(_glHandler);
        
        _orientationProvider.getOrientation(new IOrientationListener()
		{
			@Override
			public void onOrientationChanged(float azimuth, float pitch, float roll)
			{
				_model.rotate(90.0f, -90.f + azimuth, 0.0f); // TODO: apply pitch and roll
			}
		});
    }

    @Override
    public void onDrawFrame(GL10 unused)
    {    	
    	_model.draw();
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        _model.setViewport(width, height);
    }
}
