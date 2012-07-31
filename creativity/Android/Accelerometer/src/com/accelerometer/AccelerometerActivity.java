package com.accelerometer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class AccelerometerActivity extends Activity implements SensorEventListener
{
	private SensorManager _sensorManager;
	private Sensor _accelerometer;
	
	private TextView _xValue;
	private TextView _yValue;
	private TextView _zValue;
	private TextView _test;
	private ListView _log;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        _accelerometer = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        _xValue = (TextView)findViewById(R.id.xValue);
        _yValue = (TextView)findViewById(R.id.yValue);
        _zValue = (TextView)findViewById(R.id.zValue);
        _test = (TextView)findViewById(R.id.test);
        _log = (ListView)findViewById(R.id.log);
    }
    
    protected void onPause()
    {
    	super.onPause();
    	_sensorManager.unregisterListener(this);
    }
    
    protected void onResume()
    {
    	super.onResume();
    	_sensorManager.registerListener(this, _accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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
		
		if (event.values[0] > 2.0)
		{
			_test.setText(String.valueOf(event.values[0]) + ", " + _test.getText());
		}
	}
}