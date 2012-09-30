package com.fARmework.modules.SpaceGraphics.Android;

import com.fARmework.modules.SpaceGraphics.Android.Graphics.*;
import com.fARmework.modules.SpaceGraphics.Android.Graphics.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Graphics._impl.*;
import com.fARmework.modules.SpaceGraphics.Android._impl.*;

import android.content.*;
import android.graphics.*;
import android.opengl.*;
import android.util.*;

public class SpaceGraphicsView extends GLSurfaceView 
{
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
		_renderer = new GraphicsRenderer(new SensorOrientationProvider(context), new DefaultDirectionProvider(), new GLHandler());
		_renderer.setModel(new Arrow());
		
		setEGLContextClientVersion(1);
		setEGLConfigChooser(8, 8, 8, 8, 8, 0 );
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
		
		setRenderer(_renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	public void setDirectionProvider(IDirectionProvider directionProvider)
	{
		_renderer.setDirectionProvider(directionProvider);
	}
}
