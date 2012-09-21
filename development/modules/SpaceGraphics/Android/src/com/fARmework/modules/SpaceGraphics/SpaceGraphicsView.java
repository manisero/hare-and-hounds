package com.fARmework.modules.SpaceGraphics;

import com.fARmework.modules.SpaceGraphics.Graphics.GraphicsRenderer;
import com.fARmework.modules.SpaceGraphics._impl.*;

import android.content.*;
import android.opengl.*;

public class SpaceGraphicsView extends GLSurfaceView 
{	
	public SpaceGraphicsView(Context context) 
	{
		super(context);
		
		setEGLContextClientVersion(2);
		setEGLConfigChooser(true);
		setRenderer(new GraphicsRenderer(new SensorOrientationProvider(context)));
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
