package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import org.openintents.sensorsimulator.hardware.*;

import android.content.*;
import android.hardware.SensorManager;
import android.os.*;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

public class SimulatorOrientationProvider implements IOrientationProvider
{
	private SensorManagerSimulator _sensorManager;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	public SimulatorOrientationProvider(Context context)
	{
		_sensorManager = (SensorManagerSimulator)SensorManagerSimulator.getSystemService(context, Context.SENSOR_SERVICE);
		
		StrictMode.ThreadPolicy newPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(newPolicy);
		_sensorManager.connectSimulator();
	}
	
	@Override
	public void getOrientation(final IOrientationListener orientationListener)
	{
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				_gravity = event.values;
				SimulatorOrientationProvider.this.onSensorChanged(orientationListener);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(SensorEvent event)
			{
				_geomagnetic = event.values;
				SimulatorOrientationProvider.this.onSensorChanged(orientationListener);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
	}
	
	private void onSensorChanged(IOrientationListener orientationListener)
	{
		float[] rotationMatrix = new float[9];
		float[] values = new float[3];
		
		SensorManager.getRotationMatrix(rotationMatrix, null, _gravity, _geomagnetic);
		SensorManager.getOrientation(rotationMatrix, values);
		
		orientationListener.onOrientationChanged(values[0], values[1], values[2]);
	}
}
