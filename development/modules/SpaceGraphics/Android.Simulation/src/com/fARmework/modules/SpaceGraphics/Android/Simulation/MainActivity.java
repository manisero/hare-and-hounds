package com.fARmework.modules.SpaceGraphics.Android.Simulation;

import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Orientation._impl.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection._impl.*;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        SpaceGraphicsView view = new SpaceGraphicsView(this);
        view.setGraphicsRenderer(new GraphicsRenderer(new GLHandler(), new SimulatorOrientationProvider(this), new DefaultDirectionProvider()));
        view.setModel(new Arrow());
        
        setContentView(view);
    }
}
