package com.fARmework.modules.SpaceGraphics.Android.Projection;

import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Orientation.*;

import android.opengl.*;

public interface IGraphicsRenderer extends GLSurfaceView.Renderer
{
	void setDirectionProvider(IDirectionProvider directionProvider);
	
	void setModel(Model model);
}