package com.accelerometer;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import android.annotation.*;
import android.app.*;
import android.content.*;
import android.hardware.*;
import android.os.*;
import android.text.format.Time;
import android.util.*;

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
		Time time = new Time();
		time.setToNow();
		
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
				
		Data data = new Data(time.toMillis(false), x, y, z);
		
		_data.add(data);
	}
	
	@SuppressLint("WorldWriteableFiles")
	@Override
	public void onBackPressed()
	{
		BufferedWriter writer = null;
		
		try
		{
			Time time = new Time();
			time.setToNow();
			
			String filename = "acc.txt" + time.format2445() + ".csv";
			
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
