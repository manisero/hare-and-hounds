package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

public class SensorOrientationProvider implements IOrientationProvider
{
	private SensorManager _sensorManager;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	public SensorOrientationProvider(Context context)
	{
		_sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
	}
	
	@Override
	public void getOrientation(final IOrientationListener orientationListener)
	{
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				System.arraycopy(event.values, 0, _gravity, 0, 3);
				SensorOrientationProvider.this.onOrientationChanged(orientationListener);
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
				SensorOrientationProvider.this.onOrientationChanged(orientationListener);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	private void onOrientationChanged(IOrientationListener orientationListener)
	{
		float[] rotationMatrix = new float[9];
		float[] values = new float[3];

		if (SensorManager.getRotationMatrix(rotationMatrix, null, _gravity, _geomagnetic))
		{
			SensorManager.getOrientation(rotationMatrix, values);
			
			float azimuth = (float)Math.toDegrees(values[0]);
			float pitch = (float)Math.toDegrees(values[1]);
			float roll = (float)Math.toDegrees(values[2]);
			
			orientationListener.onOrientationChanged(azimuth, pitch, roll);
		}
	}
}
