package com.fARmework.modules.SpaceGraphics.Presentation;

import com.fARmework.modules.SpaceGraphics.Graphics.GraphicsRenderer;
import com.fARmework.modules.SpaceGraphics.Graphics._impl.*;

import android.content.*;
import android.opengl.*;

public class GraphicsView extends GLSurfaceView 
{	
	public GraphicsView(Context context) 
	{
		super(context);
		
		setEGLContextClientVersion(2);
		setEGLConfigChooser(true);
		setRenderer(new GraphicsRenderer(new SensorOrientationProvider(context)));
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
