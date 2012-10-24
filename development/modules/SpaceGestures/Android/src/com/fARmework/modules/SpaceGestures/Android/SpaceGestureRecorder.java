package com.fARmework.modules.SpaceGestures.Android;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGestures.Data.*;

public class SpaceGestureRecorder implements ISpaceGestureRecorder
{
	private static final int RATE = SensorManager.SENSOR_DELAY_UI;
	
	private SensorManager _sensorManager;
	private Sensor _accelerometer;
	
	private SpaceGestureData _gesture;
	
	@Override
	public void startRecording(Context context)
	{
		_sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		_accelerometer = _sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

		_gesture = new SpaceGestureData();
		_sensorManager.registerListener(this, _accelerometer, RATE);
	}
	
	@Override
	public SpaceGestureData stopRecording()
	{
		_sensorManager.unregisterListener(this);
		
		return _gesture;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}
	
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		float[] values = event.values;
		
		_gesture.addReading(values[0], values[1], values[2]);
	}
}
