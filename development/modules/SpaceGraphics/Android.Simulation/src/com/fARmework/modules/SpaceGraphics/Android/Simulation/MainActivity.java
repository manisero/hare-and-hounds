package com.fARmework.modules.SpaceGraphics.Android.Simulation;

import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection._impl.*;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        IGraphicsRenderer graphicsRenderer = new GraphicsRenderer(new GLHandler(), new SimulatorOrientationProvider(this));
        graphicsRenderer.setModel(new Arrow());
        
        SpaceGraphicsView view = new SpaceGraphicsView(this, graphicsRenderer);
        setContentView(view);
    }
}
