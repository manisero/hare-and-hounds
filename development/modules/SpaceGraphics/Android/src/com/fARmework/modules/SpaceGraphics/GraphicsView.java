package com.fARmework.modules.SpaceGraphics;

import android.content.*;
import android.opengl.*;

public class GraphicsView extends GLSurfaceView 
{
	public GraphicsView(Context context) 
	{
		super(context);
		
		setEGLContextClientVersion(2);
		setEGLConfigChooser(true);
		setRenderer(new GraphicsRenderer());
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
