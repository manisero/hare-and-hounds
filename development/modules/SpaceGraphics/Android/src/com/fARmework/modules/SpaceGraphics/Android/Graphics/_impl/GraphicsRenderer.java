package com.fARmework.modules.SpaceGraphics.Android.Graphics._impl;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.modules.SpaceGraphics.Android.Graphics.*;

public class GraphicsRenderer implements IGraphicsRenderer
{
	private IOrientationProvider _orientationProvider;
	private IDirectionProvider _directionProvider;
	private IGLHandler _glHandler;
	
    private Model _model;
    
    public GraphicsRenderer(IOrientationProvider orientationProvider, IDirectionProvider directionProvider, IGLHandler glHandler)
    {
		_orientationProvider = orientationProvider;
		_directionProvider = directionProvider;
		_glHandler = glHandler;
    }
    
    @Override
	public void setDirectionProvider(IDirectionProvider directionProvider)
	{
		_directionProvider = directionProvider;
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
		
		_glHandler.setRotationMatrix(_orientationProvider.getRotationMatrix());
		_glHandler.setDirection(_directionProvider.getDirection());
		
    	_glHandler.draw(_model);
    }
	
	@Override
    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        _glHandler.setViewport(width, height);
    }
}
