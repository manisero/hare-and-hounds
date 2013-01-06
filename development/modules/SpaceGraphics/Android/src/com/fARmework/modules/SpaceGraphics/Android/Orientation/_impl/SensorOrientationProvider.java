package com.fARmework.modules.SpaceGraphics.Android.Orientation._impl;

import android.hardware.*;
import android.view.*;

import com.fARmework.modules.SpaceGraphics.Android.Orientation.*;
import com.google.inject.*;

public class SensorOrientationProvider implements IOrientationProvider
{
	private final ISensorManagerResolver _sensorManagerResolver;
	private final IDisplayResolver _displayResolver;
	
	private SensorManager _sensorManager;
	private Display _display;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	@Inject
	public SensorOrientationProvider(ISensorManagerResolver sensorManagerResolver, IDisplayResolver displayResolver)
	{
		_sensorManagerResolver = sensorManagerResolver;
		_displayResolver = displayResolver;
		
		getSensorManager().registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				_gravity = event.values.clone();
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, getSensorManager().getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
		
		getSensorManager().registerListener(new SensorEventListener()
		{
			@Override
			public void onSensorChanged(final SensorEvent event)
			{
				_geomagnetic = event.values.clone();
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy)
			{
			}
		}, getSensorManager().getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
	}

	private SensorManager getSensorManager()
	{
		if (_sensorManager == null)
		{
			_sensorManager = _sensorManagerResolver.resolve();
		}
		
		return _sensorManager;
	}
	
	private Display getDisplay()
	{
		if (_display == null)
		{
			_display = _displayResolver.resolve();
		}
		
		return _display;
	}
	
	@Override
	public float[] getRotationMatrix()
	{
		float[] rotation = new float[16];
		
		SensorManager.getRotationMatrix(rotation, null, _gravity, _geomagnetic);

		int xAxis;
		int yAxis;
		
		switch (getDisplay().getRotation())
		{
			case Surface.ROTATION_90:
				
				xAxis = SensorManager.AXIS_Y;
				yAxis = SensorManager.AXIS_MINUS_X;
				
				break;
				
			case Surface.ROTATION_180:
				
				xAxis = SensorManager.AXIS_MINUS_X;
				yAxis = SensorManager.AXIS_MINUS_Y;
				
				break;
				
			case Surface.ROTATION_270:
				
				xAxis = SensorManager.AXIS_MINUS_Y;
				yAxis = SensorManager.AXIS_X;
				
				break;
				
			default:
				
				xAxis = SensorManager.AXIS_X;
				yAxis = SensorManager.AXIS_Y;
		}
		
		float[] remappedRotation = new float[16];
		
		SensorManager.remapCoordinateSystem(rotation, xAxis, yAxis, remappedRotation);
		
		return remappedRotation;
	}
}
