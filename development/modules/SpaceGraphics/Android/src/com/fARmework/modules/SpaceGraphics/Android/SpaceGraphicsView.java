package com.fARmework.modules.SpaceGraphics.Android;

import android.content.*;
import android.graphics.*;
import android.opengl.*;
import android.util.*;

import com.fARmework.modules.SpaceGraphics.Android.Orientation._impl.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection._impl.*;

public class SpaceGraphicsView extends GLSurfaceView 
{
	private IGraphicsRenderer _graphicsRenderer;
	
	public SpaceGraphicsView(Context context) 
	{
		super(context);
		initialize();
	}
	
	public SpaceGraphicsView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize();
	}
	
	public SpaceGraphicsView(Context context, IGraphicsRenderer graphicsRenderer) 
	{
		super(context);
		setGraphicsRenderer(graphicsRenderer);
		initialize();
	}
	
	private void initialize()
	{
		setEGLContextClientVersion(1);
		setEGLConfigChooser(8, 8, 8, 8, 8, 0 );
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
		
		if (_graphicsRenderer == null)
		{
			setGraphicsRenderer(new GraphicsRenderer(new GLHandler(), new SensorOrientationProvider(new SensorManagerResolver(getContext()), new DisplayResolver(getContext()))));
		}
		
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	public IGraphicsRenderer getGraphicsRenderer()
	{
		return _graphicsRenderer;
	}
	
	public void setGraphicsRenderer(IGraphicsRenderer graphicsRenderer)
	{
		_graphicsRenderer = graphicsRenderer;
		setRenderer(graphicsRenderer);
	}
}
