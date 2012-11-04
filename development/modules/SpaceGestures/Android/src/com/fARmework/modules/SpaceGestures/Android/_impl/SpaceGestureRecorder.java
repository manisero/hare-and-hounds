package com.fARmework.modules.SpaceGestures.Android._impl;

import android.hardware.*;

import com.fARmework.modules.SpaceGestures.Android.*;
import com.fARmework.modules.SpaceGestures.Data.*;
import com.google.inject.*;

public class SpaceGestureRecorder implements ISpaceGestureRecorder
{
	private static final int SENSOR_TYPE = Sensor.TYPE_LINEAR_ACCELERATION;
	private static final int RATE = SensorManager.SENSOR_DELAY_UI;
	
	private final ISensorManagerResolver _sensorManagerResolver;
	
	private SensorManager _sensorManager;
	private SpaceGestureData _gesture;
	
	@Inject
	public SpaceGestureRecorder(ISensorManagerResolver sensorManagerResolver)
	{
		_sensorManagerResolver = sensorManagerResolver;
	}
	
	private SensorManager getSensorManager()
	{
		if (_sensorManager == null)
		{
			_sensorManager = _sensorManagerResolver.resolve();
		}
		
		return _sensorManager;
	}
	
	@Override
	public void startRecording()
	{
		_gesture = new SpaceGestureData();
		
		Sensor _accelerometer = getSensorManager().getDefaultSensor(SENSOR_TYPE);
		getSensorManager().registerListener(this, _accelerometer, RATE);
	}
	
	@Override
	public SpaceGestureData stopRecording()
	{
		getSensorManager().unregisterListener(this);
		
		return _gesture;
	}
	
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		_gesture.addReading(event.values[0], event.values[1], event.values[2]);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}
}
