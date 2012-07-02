package com.Compass;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class CompassActivity extends Activity implements SensorEventListener
{
	private TextView _azimutText;
	
	private float _azimuth;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _azimutText = (TextView)findViewById(R.id.azimut);
        
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		_azimuth = event.values[0];
		_azimutText.setText(String.valueOf(_azimuth));
	}
    
    
}