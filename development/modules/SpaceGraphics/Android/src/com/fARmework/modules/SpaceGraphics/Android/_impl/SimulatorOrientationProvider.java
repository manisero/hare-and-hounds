package com.fARmework.modules.SpaceGraphics.Android._impl;

import org.openintents.sensorsimulator.hardware.*;

import android.content.*;
import android.hardware.SensorManager;
import android.os.*;
import android.view.*;

import com.fARmework.modules.SpaceGraphics.Android.*;

public class SimulatorOrientationProvider implements IOrientationProvider
{
	private SensorManagerSimulator _sensorManager;
	private Display _display;
	
	private float[] _gravity = new float[3];
	private float[] _geomagnetic = new float[3];
	
	public SimulatorOrientationProvider(Context context)
	{
		_sensorManager = (SensorManagerSimulator)SensorManagerSimulator.getSystemService(context, Context.SENSOR_SERVICE);
		
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE); 
		_display = windowManager.getDefaultDisplay();
		
		StrictMode.ThreadPolicy newPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(newPolicy);
		_sensorManager.connectSimulator();
		
		_sensorManager.registerListener(new SensorEventListener()
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
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
		
		_sensorManager.registerListener(new SensorEventListener()
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
		}, _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManagerSimulator.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public float[] getRotationMatrix()
	{
		float[] rotation = new float[16];
		
		SensorManager.getRotationMatrix(rotation, null, _gravity, _geomagnetic);
		
		int xAxis = SensorManager.AXIS_X;
		int yAxis = SensorManager.AXIS_Y;
		
		switch(_display.getRotation())
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
		}
		
		float[] remappedRotation = new float[16];
		
		SensorManager.remapCoordinateSystem(rotation, xAxis, yAxis, remappedRotation);
		
		return remappedRotation;
	}
}
