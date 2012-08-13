package com.fARmework.modules.SpaceGestures.Android;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;
import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.Direction;

import android.content.Context;
import android.hardware.*;
import android.os.CountDownTimer;

public class SpaceGestureRecorder implements ISpaceGestureRecorder
{
	private static final double THRESHOLD = 3;
	private static final int RATE = SensorManager.SENSOR_DELAY_UI;
	private static final int SLEEP_TIME = 250;
	
	private SensorManager _sensorManager;
	private Sensor _accelerometer;
	
	private Direction _currentDirection;
	private Direction _detectedDirection;
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
		_currentDirection = null;
		_detectedDirection = null;
		
		return _gesture;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}
	
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		if (event.values[0] < THRESHOLD && event.values[0] > -1.0 * THRESHOLD &&
			event.values[1] < THRESHOLD && event.values[1] > -1.0 * THRESHOLD &&
			event.values[2] < THRESHOLD && event.values[2] > -1.0 * THRESHOLD)
		{
			return;
		}
		
		if (Math.abs(event.values[0]) > Math.abs(event.values[1]) &&
			Math.abs(event.values[0]) > Math.abs(event.values[2]))
		{
			if (event.values[0] > 0.0)
			{
				if (_currentDirection == null)
				{
					_currentDirection = Direction.Right;
				}
				else if (_currentDirection == Direction.Left)
				{
					_detectedDirection = Direction.Left;
				}
			}
			else
			{
				if (_currentDirection == null)
				{
					_currentDirection = Direction.Left;
				}
				else if (_currentDirection == Direction.Right)
				{
					_detectedDirection = Direction.Right;
				}
			}
		}
		else if (Math.abs(event.values[1]) > Math.abs(event.values[0]) &&
				 Math.abs(event.values[1]) > Math.abs(event.values[2]))
		{
			if (event.values[1] > 0.0)
			{
				if (_currentDirection == null)
				{
					_currentDirection = Direction.Up;
				}
				else if (_currentDirection == Direction.Down)
				{
					_detectedDirection = Direction.Down;
				}
			}
			else
			{
				if (_currentDirection == null)
				{
					_currentDirection = Direction.Down;
				}
				else if (_currentDirection == Direction.Up)
				{
					_detectedDirection = Direction.Up;
				}
			}
		}
		else if (Math.abs(event.values[2]) > Math.abs(event.values[0]) &&
				 Math.abs(event.values[2]) > Math.abs(event.values[1]))
		{
			if (event.values[2] > 0.0)
			{
				if (_currentDirection == null)
				{
					_currentDirection = Direction.Forward;
				}
				else if (_currentDirection == Direction.Backward)
				{
					_detectedDirection = Direction.Backward;
				}
			}
			else
			{
				if (_currentDirection == null)
				{
					_currentDirection = Direction.Backward;
				}
				else if (_currentDirection == Direction.Forward)
				{
					_detectedDirection = Direction.Forward;
				}
			}
		}
		
		if (_detectedDirection != null)
		{
			_gesture.addDirection(_detectedDirection);
			_sensorManager.unregisterListener(this);
			
			new CountDownTimer(SLEEP_TIME, SLEEP_TIME)
			{
				@Override
				public void onTick(long millisUntilFinished)
				{
				}
				
				@Override
				public void onFinish()
				{
					_currentDirection = null;
					_detectedDirection = null;
					_sensorManager.registerListener(SpaceGestureRecorder.this, _accelerometer, RATE);
				}
			}.start();
		}
	}
}
