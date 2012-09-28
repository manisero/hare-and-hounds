package com.fARmework.modules.SpaceGraphics.Android;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(new SpaceGraphicsView(this));
    }
}
