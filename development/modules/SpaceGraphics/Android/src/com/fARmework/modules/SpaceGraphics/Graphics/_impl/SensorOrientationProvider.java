package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import android.content.*;
import android.hardware.*;
import android.widget.*;

import com.fARmework.modules.SpaceGraphics.Graphics.IOrientationProvider;

public class SensorOrientationProvider implements IOrientationProvider
{
	private Context mContext;
	private SensorManager _sensorManager;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	public SensorOrientationProvider(Context context)
	{
		mContext = context;
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
				_gravity = event.values;
				//SensorOrientationProvider.this.onSensorChanged(orientationListener);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		
		_sensorManager.registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(SensorEvent event)
			{
				_geomagnetic = event.values;
				//SensorOrientationProvider.this.onSensorChanged(orientationListener);
				
				orientationListener.onOrientationChanged(event.values[0], event.values[1], event.values[2]);
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	private void onSensorChanged(IOrientationListener orientationListener)
	{
		float[] rotationMatrix = new float[9];
		float[] values = new float[3];
		
		SensorManager.getRotationMatrix(rotationMatrix, null, _gravity, _geomagnetic);
		SensorManager.getOrientation(rotationMatrix, values);
		
		orientationListener.onOrientationChanged(values[0] * 180.0f / 3.14f, values[1] * 180.0f / 3.14f, values[2] * 180.0f / 3.14f);
		
		/*
		Toast.makeText(mContext, "A: " + values[0] * 180.0 / 3.14 + "\nP: " + values[1] * 180.0 / 3.14 + "\nR: " + values[2] * 180.0 / 3.14, Toast.LENGTH_SHORT).show();
		try { Thread.sleep(2000); }
		catch (InterruptedException e) {}
		*/
	}
}
