package com.fARmework.modules.SpaceGraphics._impl;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGraphics.*;

public class SensorOrientationProvider implements IOrientationProvider
{
	private SensorManager _sensorManager;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	private float[] _lastRotationMatrix = new float[16];
	
	public SensorOrientationProvider(Context context)
	{
		_sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				_gravity = event.values.clone();
				SensorManager.getRotationMatrix(_lastRotationMatrix, null, _gravity, _geomagnetic);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				_geomagnetic = event.values.clone();
				SensorManager.getRotationMatrix(_lastRotationMatrix, null, _gravity, _geomagnetic);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public float[] getRotationMatrix()
	{
		return _lastRotationMatrix;
	}
}
