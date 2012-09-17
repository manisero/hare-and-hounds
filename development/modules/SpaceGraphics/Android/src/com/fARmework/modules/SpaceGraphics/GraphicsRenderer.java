package com.fARmework.modules.SpaceGraphics;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import android.content.*;
import android.hardware.*;
import android.opengl.*;

public class GraphicsRenderer implements GLSurfaceView.Renderer, SensorEventListener
{
    private Arrow _arrow;
    private GLHandler _glHandler;
    
	private SensorManager _sensorManager;
	private Sensor _sensor;
	
	private final int SENSOR_DELAY = SensorManager.SENSOR_DELAY_FASTEST;    
    
    public GraphicsRenderer(Context context)
    {
		_sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		_sensor = _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
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

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
		return;		
	}

	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		if(_arrow != null)
		{
			_arrow.rotate(event.values[0], event.values[1], event.values[2]);
		}
	}
}
