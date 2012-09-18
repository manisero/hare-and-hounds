package com.fARmework.modules.SpaceGraphics;

import android.content.*;
import android.opengl.*;
import android.os.*;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import org.openintents.sensorsimulator.hardware.*;

public class GraphicsRenderer implements GLSurfaceView.Renderer, SensorEventListener
{
    private Arrow _arrow;
    private GLHandler _glHandler;
    
	private SensorManagerSimulator _sensorManager;	
	private Sensor _sensor;
	
	private final int SENSOR_DELAY = SensorManagerSimulator.SENSOR_DELAY_NORMAL;

    
    public GraphicsRenderer(Context context)
    {
		_sensorManager = SensorManagerSimulator.getSystemService(context, Context.SENSOR_SERVICE);
		_sensor = _sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		connectSimulator();
		_sensorManager.registerListener(this, _sensor, SENSOR_DELAY);
    }
    
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) 
    {
        _glHandler = new GLHandler();
        _arrow = new Arrow(_glHandler);    	
    }

    @Override
    public void onDrawFrame(GL10 unused) 
    {    	
    	_arrow.draw();
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) 
    {
        _arrow.setViewport(width, height);
    }
    
    public void connectSimulator()
    {	
    	/*
    	 * 	Notice: it's TERRIBLE idea to change a thread policy like this.
    	 * 	However, it seems the only reasonable way to start the sensors simulator.
    	 * 
    	 * 	TODO: remove this code as soon as the sensors simulator is not needed
    	 */
    	
		StrictMode.ThreadPolicy newPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(newPolicy); 
		
		_sensorManager.connectSimulator();	
    }
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
	}

	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		if(_arrow != null)
		{
			pointToTheNorth(event.values[0], event.values[1], event.values[2]);
		}
	}
	
	public void pointToTheNorth(float azimuth, float pitch, float roll)
	{
		_arrow.rotate(90.0f, -90.f + azimuth, 0.0f);
	}
}
