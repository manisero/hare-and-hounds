package com.accelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AccelerometerActivity extends Activity implements SensorEventListener
{
	private enum Direction
	{
		Up, Down,
		Left, Right,
		Forward, Backward
	}
	
	private static final double THRESHOLD = 3;
	
	private SensorManager _sensorManager;
	private Sensor _accelerometer;
	
	private TextView _xValue;
	private TextView _yValue;
	private TextView _zValue;
	private TextView _test;
	private Button _clear;
	
	private Direction _currentDirection;
	private Direction _detectedDirection;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        _accelerometer = _sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        
        _xValue = (TextView)findViewById(R.id.xValue);
        _yValue = (TextView)findViewById(R.id.yValue);
        _zValue = (TextView)findViewById(R.id.zValue);
        _test = (TextView)findViewById(R.id.test);
        _clear = (Button)findViewById(R.id.clearButton);
        
        _clear.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_test.setText("");
				_currentDirection = null;
				_detectedDirection = null;
			}
		});
    }
    
    protected void onPause()
    {
    	super.onPause();
    	_sensorManager.unregisterListener(this);
    }
    
    protected void onResume()
    {
    	super.onResume();
    	_sensorManager.registerListener(this, _accelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		_xValue.setText(String.valueOf(event.values[0]));
		_yValue.setText(String.valueOf(event.values[1]));
		_zValue.setText(String.valueOf(event.values[2]));
		
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
			_sensorManager.unregisterListener(this);
			
			new CountDownTimer(200, 200)
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
					_sensorManager.registerListener(AccelerometerActivity.this, _accelerometer, SensorManager.SENSOR_DELAY_UI);
				}
			}.start();
			
			_test.setText(_test.getText() + _detectedDirection.toString() + ", ");
			_currentDirection = null;
			_detectedDirection = null;
		}
	}
}
