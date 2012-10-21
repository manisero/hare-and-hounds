package com.fARmework.modules.SpaceGraphics.Android.Simulation;

import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.modules.SpaceGraphics.Android.Models.*;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        SpaceGraphicsView view = new SpaceGraphicsView(this, new SimulatorOrientationProvider(this));
        //SpaceGraphicsView view = new SpaceGraphicsView(this); // uncomment to use SensorOrientationProvider
        view.setModel(new Arrow());
        
        setContentView(view);
    }
}
