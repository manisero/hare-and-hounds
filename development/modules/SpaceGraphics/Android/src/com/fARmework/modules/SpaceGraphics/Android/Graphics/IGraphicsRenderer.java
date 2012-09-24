package com.fARmework.modules.SpaceGraphics.Android.Graphics;

import com.fARmework.modules.SpaceGraphics.Android.*;

import android.opengl.*;

public interface IGraphicsRenderer extends GLSurfaceView.Renderer
{
	void setDirectionProvider(IDirectionProvider directionProvider);
	
	void setModel(Model model);
}