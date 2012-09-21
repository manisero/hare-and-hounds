package com.fARmework.modules.SpaceGraphics;

import com.fARmework.modules.SpaceGraphics.Graphics.*;
import com.fARmework.modules.SpaceGraphics.Graphics.Models.*;
import com.fARmework.modules.SpaceGraphics.Graphics._impl.*;
import com.fARmework.modules.SpaceGraphics._impl.*;

import android.content.*;
import android.opengl.*;

public class SpaceGraphicsView extends GLSurfaceView 
{
	public SpaceGraphicsView(Context context) 
	{
		super(context);
		
		IGraphicsRenderer renderer = new GraphicsRenderer(new SensorOrientationProvider(context), new DefaultDirectionProvider());
		renderer.setModel(new Arrow());
		
		setEGLContextClientVersion(2);
		setEGLConfigChooser(true);
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
