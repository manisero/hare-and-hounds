package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import com.fARmework.modules.SpaceGraphics.*;
import com.fARmework.modules.SpaceGraphics.Graphics.*;
import com.fARmework.modules.SpaceGraphics.IOrientationProvider.*;

public class GraphicsRenderer implements IGraphicsRenderer
{
	private IOrientationProvider _orientationProvider;
	private IGLHandler _glHandler;
	
    private Model _model;
    
    public GraphicsRenderer(IOrientationProvider orientationProvider)
    {
		_orientationProvider = orientationProvider;
		
		_orientationProvider.getOrientation(new IOrientationListener()
		{
			@Override
			public void onOrientationChanged(float azimuth, float pitch, float roll)
			{
				if (_model == null)
					return;
				
				_model.rotate(0.0f, azimuth, 0.0f); // TODO: properly implement rotating
			}
		});
    }
    
    @Override
	public void setModel(Model model)
    {
    	_model = model;
    }
    
	@Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) 
    {
        _glHandler = new GLHandler();
    }
	
	@Override
    public void onDrawFrame(GL10 unused)
    {    	
    	_glHandler.draw(_model);
    }
	
	@Override
    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        _glHandler.setViewport(width, height);
    }
}
