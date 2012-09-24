package com.fARmework.modules.SpaceGraphics._impl;

import org.openintents.sensorsimulator.hardware.*;

import android.content.*;
import android.hardware.SensorManager;
import android.os.*;

import com.fARmework.modules.SpaceGraphics.*;

public class SimulatorOrientationProvider implements IOrientationProvider
{
	private SensorManagerSimulator _sensorManager;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	private float[] _rotationMatrix = new float[16];	
	
	public SimulatorOrientationProvider(Context context)
	{
		_sensorManager = (SensorManagerSimulator)SensorManagerSimulator.getSystemService(context, Context.SENSOR_SERVICE);
		
		StrictMode.ThreadPolicy newPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(newPolicy);
		_sensorManager.connectSimulator();
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				_gravity = event.values.clone();
				SensorManager.getRotationMatrix(_rotationMatrix, null, _gravity, _geomagnetic);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManagerSimulator.SENSOR_DELAY_GAME);
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				_geomagnetic = event.values.clone();
				SensorManager.getRotationMatrix(_rotationMatrix, null, _gravity, _geomagnetic);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManagerSimulator.SENSOR_DELAY_GAME);
	}
	
	@Override
	public float[] getRotationMatrix()
	{
		return _rotationMatrix;
	}
}
