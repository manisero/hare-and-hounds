package com.fARmework.modules.SpaceGraphics.Android.Projection._impl;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Orientation.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;
import com.google.inject.*;

public class GraphicsRenderer implements IGraphicsRenderer
{
	private final IGLHandler _glHandler;
	private final IOrientationProvider _orientationProvider;
	private IDirectionProvider _directionProvider;
	
    private Model _model;
    
    @Inject
    public GraphicsRenderer(IGLHandler glHandler, IOrientationProvider orientationProvider, IDirectionProvider directionProvider)
    {
    	_glHandler = glHandler;
		_orientationProvider = orientationProvider;
		_directionProvider = directionProvider;
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
    public void onSurfaceCreated(GL10 gl, EGLConfig config) 
    {
		_glHandler.initialize(gl);
    }
	
	@Override
    public void onDrawFrame(GL10 gl)
    {
		if (_model == null)
			return;
		
		_glHandler.setRotationMatrix(_orientationProvider.getRotationMatrix());
		_glHandler.setDirection(_directionProvider.getDirection());
		
    	_glHandler.draw(gl, _model);
    }
	
	@Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        _glHandler.setViewport(gl, width, height);
    }
}
