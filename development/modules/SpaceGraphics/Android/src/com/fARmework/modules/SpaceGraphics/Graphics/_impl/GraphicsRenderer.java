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
    
    public GraphicsRenderer(IOrientationProvider orientationProvider, IDirectionProvider directionProvider, IGLHandler glHandler)
    {
		_orientationProvider = orientationProvider;
		_directionProvicer = directionProvider;
		_glHandler = glHandler;
    }
    
    @Override
	public void setModel(Model model)
    {
    	_model = model;
    }
    
	@Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) 
    {
		_glHandler.initialize();
    }
	
	@Override
    public void onDrawFrame(GL10 unused)
    {
		if (_model == null)
			return;
		
		_model.rotate(	_orientationProvider.getOrientation().Roll, 
						_orientationProvider.getOrientation().Azimuth - _directionProvicer.getDirection(), 
						-_orientationProvider.getOrientation().Pitch); // TODO: properly implement rotating
    	_glHandler.draw(_model);
    }
	
	@Override
    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        _glHandler.setViewport(width, height);
    }
}
