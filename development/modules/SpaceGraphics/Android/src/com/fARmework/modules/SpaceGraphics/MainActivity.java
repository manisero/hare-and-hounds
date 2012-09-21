package com.fARmework.modules.SpaceGraphics;

import android.app.*;
import android.opengl.*;
import android.os.*;

public class MainActivity extends Activity
{
	private GLSurfaceView _surfaceView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        _surfaceView = new SpaceGraphicsView(this);
        setContentView(_surfaceView);   
    }
}
