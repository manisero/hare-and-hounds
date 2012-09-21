package com.fARmework.modules.SpaceGraphics._impl;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGraphics.*;

public class SensorOrientationProvider implements IOrientationProvider
{
	private SensorManager _sensorManager;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	private float _lastAzimuth;
	private float _lastPitch;
	private float _lastRoll;
	
	public SensorOrientationProvider(Context context)
	{
		_sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				System.arraycopy(event.values, 0, _gravity, 0, 3);
				SensorOrientationProvider.this.onOrientationChanged();
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
				System.arraycopy(event.values, 0, _geomagnetic, 0, 3);
				SensorOrientationProvider.this.onOrientationChanged();
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public Orientation getOrientation()
	{
		return new Orientation(_lastAzimuth, _lastPitch, _lastRoll);
	}
	
	private void onOrientationChanged()
	{
		float[] rotationMatrix = new float[9];
		float[] values = new float[3];

		if (SensorManager.getRotationMatrix(rotationMatrix, null, _gravity, _geomagnetic))
		{
			SensorManager.getOrientation(rotationMatrix, values);
			
			_lastAzimuth = (float)Math.toDegrees(values[0]);
			_lastPitch = (float)Math.toDegrees(values[1]);
			_lastRoll = (float)Math.toDegrees(values[2]);
		}
	}
}
