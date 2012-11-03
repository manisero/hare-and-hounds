package com.fARmework.modules.SpaceGraphics.Android;

import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;
import com.google.inject.*;

import android.content.*;
import android.graphics.*;
import android.opengl.*;
import android.util.*;

public class SpaceGraphicsView extends GLSurfaceView 
{
	@Inject
	private IGraphicsRenderer _renderer;
	
	public SpaceGraphicsView(Context context) 
	{
		super(context);
		initialize(context);
	}
	
	public SpaceGraphicsView(Context context, AttributeSet attrs)
	{
		super(context); // Calling super(context, attrs) does not work (the view is not rendered)...
		initialize(context);
	}
	
	private void initialize(Context context)
	{
		setEGLContextClientVersion(1);
		setEGLConfigChooser(8, 8, 8, 8, 8, 0 );
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
		
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	public void setGraphicsRenderer(IGraphicsRenderer graphicsRenderer)
	{
		_renderer = graphicsRenderer;
		setRenderer(graphicsRenderer);
	}
	
	public void setModel(Model model)
	{
		_renderer.setModel(model);
	}
	
	public void setDirectionProvider(IDirectionProvider directionProvider)
	{
		_renderer.setDirectionProvider(directionProvider);
	}
}
