package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

public class SensorOrientationProvider implements IOrientationProvider
{
	private SensorManager _sensorManager;
	
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
				orientationListener.onOrientationChanged(event.values[0], event.values[1], event.values[2]);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
	}
}
