package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import com.fARmework.modules.SpaceGraphics.*;
import com.fARmework.modules.SpaceGraphics.Graphics.*;

public class GraphicsRenderer implements IGraphicsRenderer
{
	private IOrientationProvider _orientationProvider;
	private IDirectionProvider _directionProvicer;
	private IGLHandler _glHandler;
	
    private Model _model;
    
    public GraphicsRenderer(IOrientationProvider orientationProvider, IDirectionProvider directionProvider)
    {
		_orientationProvider = orientationProvider;
		_directionProvicer = directionProvider;
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
		_model.rotate(0.0f, _orientationProvider.getOrientation().Azimuth - _directionProvicer.getDirection(), 0.0f); // TODO: properly implement rotating
    	_glHandler.draw(_model);
    }
	
	@Override
    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        _glHandler.setViewport(width, height);
    }
}
