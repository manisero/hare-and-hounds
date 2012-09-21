package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import org.openintents.sensorsimulator.hardware.*;

import android.content.*;
import android.os.*;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

public class SimulatorOrientationProvider implements IOrientationProvider
{
	private SensorManagerSimulator _sensorManager;
	
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
				orientationListener.onOrientationChanged(event.values[0], event.values[1], event.values[2]);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
	}
}
