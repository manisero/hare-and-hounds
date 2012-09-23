package com.fARmework.modules.SpaceGraphics.Android;

import com.fARmework.modules.SpaceGraphics.Android.Graphics.*;
import com.fARmework.modules.SpaceGraphics.Android.Graphics.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Graphics._impl.*;
import com.fARmework.modules.SpaceGraphics.Android._impl.*;

import android.content.*;
import android.opengl.*;
import android.util.*;

public class SpaceGraphicsView extends GLSurfaceView 
{
	public SpaceGraphicsView(Context context) 
	{
		super(context);
		initialize(context);
	}
	
	public SpaceGraphicsView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize(context);
	}
	
	private void initialize(Context context)
	{
		IGraphicsRenderer renderer = new GraphicsRenderer(new SensorOrientationProvider(context), new DefaultDirectionProvider(), new GLHandler());
		renderer.setModel(new Arrow());
		
		setEGLContextClientVersion(2);
		setEGLConfigChooser(true);
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
