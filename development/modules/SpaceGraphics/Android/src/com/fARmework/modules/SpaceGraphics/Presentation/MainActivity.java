package com.fARmework.modules.SpaceGraphics.Presentation;

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
        
        _surfaceView = new GraphicsView(this);
        setContentView(_surfaceView);   
    }
}
