package com.accelerometer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class AccelerometerActivity extends Activity implements SensorEventListener
{
	private DecimalFormat format = new DecimalFormat();
	
	public AccelerometerActivity()
	{
		DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		format.setDecimalFormatSymbols(symbols);
		format.setGroupingUsed(false);
	}
	
	private class Data
	{
		public long TimeStamp;
		public float X;
		public float Y;
		public float Z;
		
		public Data(long timeStamp, float x, float y, float z)
		{
			TimeStamp = timeStamp;
			X = x;
			Y = y;
			Z = z;
		}
		
		@Override
		public String toString()
		{
			return "" + TimeStamp + ";" + format.format(X) + ";" + format.format(Y) + ";" + format.format(Z);
		}
	}
	
	private SensorManager _sensorManager;
	private Sensor _accelerometer;
	
	private List<Data> _data = new LinkedList<Data>();
	
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
				
		Data data = new Data(System.currentTimeMillis(), x, y, z);
		
		_data.add(data);
	}
	
	@SuppressLint("WorldWriteableFiles")
	@Override
	public void onBackPressed()
	{
		BufferedWriter writer = null;
		
		try
		{	
			String filename = "acc_" + System.currentTimeMillis() + ".csv";
			
			writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(filename, Context.MODE_WORLD_WRITEABLE)));
			
			for (Data data : _data)
			{
				writer.write(data.toString() + "\n");
			}
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
