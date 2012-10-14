package com.accelerometer;

import java.io.*;
import java.util.*;

import android.annotation.*;
import android.app.*;
import android.content.*;
import android.hardware.*;
import android.os.*;
import android.util.*;

public class AccelerometerActivity extends Activity implements SensorEventListener
{
	private class Data
	{
		public float X;
		public float Y;
		public float Z;
		
		public Data(float x, float y, float z)
		{
			X = x;
			Y = y;
			Z = z;
		}
		
		@Override
		public String toString()
		{
			return "" + X + "\t" + Y + "\t" + Z;
		}
	}
	
	private enum Direction
	{
		Up,
		Down,
		Left,
		Right
	}
	
	private static final float THRESHOLD = 5;
	private static final int FILTER_SAMPLES = 5;
	
	private SensorManager _sensorManager;
	private Sensor _accelerometer;
	
	private List<Data> _data = new LinkedList<Data>();
	private List<String> _gestures = new LinkedList<String>();
	private LinkedList<Data> _samples = new LinkedList<Data>();
	
	private float _lastX;
	private float _lastY;
	private float _lastZ;
	
	private Direction _currentX;
	private Direction _currentY;
	private Direction _detectedX;
	private Direction _detectedY;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        _accelerometer = _sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
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
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
				
		Data delta = new Data(x - _lastX, y - _lastY, z - _lastZ);
		
		float filteredX = 0.0f;
		float filteredY = 0.0f;
		float filteredZ = 0.0f;
		
		for (Data sample : _samples)
		{
			filteredX += sample.X;
			filteredY += sample.Y;
			filteredZ += sample.Z;
		}
		
		filteredX += delta.X;
		filteredY += delta.Y;
		filteredZ += delta.Z;
		
		delta.X = filteredX / (_samples.size() + 1);
		delta.Y = filteredY / (_samples.size() + 1);
		delta.Z = filteredZ / (_samples.size() + 1);
		
		_data.add(delta);
		
		_samples.addFirst(delta);
		
		if (_samples.size() >= FILTER_SAMPLES)
		{
			_samples.removeLast();
		}
		
		/*if (Math.abs(delta.X) >= THRESHOLD)
		{		
			if (delta.X > 0)
			{
				if (_currentX == null)
				{
					_currentX = Direction.Right;
				}
				else if (_currentX == Direction.Left)
				{
					_detectedX = Direction.Right;
				}
			}
			else
			{
				if (_currentX == null)
				{
					_currentX = Direction.Left;
				}
				else if (_currentX == Direction.Right)
				{
					_detectedX = Direction.Left;
				}
			}
		}
		
		if (Math.abs(delta.Y) >= THRESHOLD)
		{
			if (delta.Y > 0)
			{
				if (_currentY == null)
				{
					_currentY = Direction.Up;
				}
				else if (_currentY == Direction.Down)
				{
					_detectedY = Direction.Up;
				}
			}
			else
			{
				if (_currentY == null)
				{
					_currentY = Direction.Down;
				}
				else if (_currentY == Direction.Up)
				{
					_detectedY = Direction.Down;
				}
			}
		}
		
		if (_detectedX != null)
		{
			_gestures.add(_detectedX.toString());
			
			_detectedX = null;
			_currentX = null;
		}
		
		if (_detectedY != null)
		{
			_gestures.add(_detectedY.toString());
			
			_detectedY = null;
			_currentY = null;
		}*/
				
		_lastX = x;
		_lastY = y;
		_lastZ = z;
	}
	
	@SuppressLint("WorldWriteableFiles")
	@Override
	public void onBackPressed()
	{
		BufferedWriter writer = null;
		
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("acc.txt", Context.MODE_WORLD_WRITEABLE)));
			
			for (Data data : _data)
			{
				writer.write(data.toString() + "\n");
			}
			
			/*writer.write("\n\n");
			
			for (String gesture : _gestures)
			{
				writer.write(gesture + " ");
			}*/
		}
		catch(Exception e)
		{
			Log.d("Exception", e.getMessage());
		}
		finally 
		{
			if(writer != null)
			{
				try
				{
					writer.close();
				}
				catch(IOException e)
				{
					Log.d("Exception", e.getMessage());
				}
			}
		}
		
		super.onBackPressed();
	}
}
