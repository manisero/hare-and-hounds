package com.fARmework.modules.SpaceGraphics.Presentation;

import com.fARmework.modules.SpaceGraphics.Graphics.GraphicsRenderer;

import android.content.*;
import android.opengl.*;

public class GraphicsView extends GLSurfaceView 
{	
	public GraphicsView(Context context) 
	{
		super(context);
		
		setEGLContextClientVersion(2);
		setEGLConfigChooser(true);
		setRenderer(new GraphicsRenderer(context));
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
