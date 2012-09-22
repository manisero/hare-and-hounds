package com.fARmework.modules.SpaceGraphics._impl;

import org.openintents.sensorsimulator.hardware.*;

import android.content.*;
import android.os.*;

import com.fARmework.modules.SpaceGraphics.*;

public class SimulatorOrientationProvider implements IOrientationProvider
{
	private SensorManagerSimulator _sensorManager;
	
	private float _lastAzimuth;
	private float _lastPitch;
	private float _lastRoll;
	
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
				_lastAzimuth = event.values[0];
				_lastPitch = event.values[1];
				_lastRoll = event.values[2];
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public Orientation getOrientation()
	{
		return new Orientation(_lastAzimuth, _lastPitch, _lastRoll);
	}

	@Override
	public float[] getRotationMatrix()
	{
		return null;
	}
}
