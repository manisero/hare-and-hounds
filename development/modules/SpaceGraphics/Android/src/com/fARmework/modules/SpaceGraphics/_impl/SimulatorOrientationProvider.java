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
	private float[] _inclinationMatrix = new float[16];
	
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
				System.arraycopy(event.values, 0, _gravity, 0, 3);
				SimulatorOrientationProvider.this.onOrientationChanged();
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				System.arraycopy(event.values, 0, _geomagnetic, 0, 3);
				SimulatorOrientationProvider.this.onOrientationChanged();
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public Orientation getOrientation()
	{
		return new Orientation(_lastAzimuth, _lastPitch, _lastRoll);
	}

	private void onOrientationChanged()
	{
		float[] values = new float[3];
		
		if (SensorManager.getRotationMatrix(_rotationMatrix, _inclinationMatrix, _gravity, _geomagnetic))
		{
			SensorManager.getOrientation(_rotationMatrix, values);
			
			_lastAzimuth = (float)Math.toDegrees(values[0]);
			_lastPitch = (float)Math.toDegrees(values[1]);
			_lastRoll = (float)Math.toDegrees(values[2]);
		}
	}

	@Override
	public float[] getRotationMatrix()
	{
		return _rotationMatrix;
	}
}
