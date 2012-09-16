package com.fARmework.modules.SpaceGraphics;

import android.opengl.*;
import android.os.Bundle;
import android.app.Activity;

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
