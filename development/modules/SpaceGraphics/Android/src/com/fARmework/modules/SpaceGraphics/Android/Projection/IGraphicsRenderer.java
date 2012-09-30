package com.fARmework.modules.SpaceGraphics.Android.Projection;

import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.modules.SpaceGraphics.Android.Models.*;

import android.opengl.*;

public interface IGraphicsRenderer extends GLSurfaceView.Renderer
{
	void setDirectionProvider(IDirectionProvider directionProvider);
	
	void setModel(Model model);
}