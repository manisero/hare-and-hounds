package com.GPS;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GPSActivity extends Activity implements LocationListener
{
	private List<String> _points;
	private List<Double> _longitudes;
	private List<Double> _latitudes;
	
	private double _currentError;
	private double _currentLongitude;
	private double _currentLatitude;
	private int _currentPoint;
	
	private TextView _latitudeText;
	private TextView _longitudeText;
	private View _mark;
	private TextView _streetText;
	private Button _toggleErrorButton;
	private TextView _currentErrorText;
	private Button _registerButton;
	
	private LocationManager _manager;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _points = new ArrayList<String>();
        _longitudes = new ArrayList<Double>();
        _latitudes = new ArrayList<Double>();
        
        /*
        _points.add("A");
        _latitudes.add(52.129761);
        _longitudes.add(21.060408);
        
        _points.add("B");
        _latitudes.add(52.128877);
        _longitudes.add(21.060445);
        
        _points.add("C");
        _latitudes.add(52.129793);
        _longitudes.add(21.061840);

        _points.add("D");
        _latitudes.add(52.128902);
        _longitudes.add(21.061854);
        
        _points.add("E");
        _latitudes.add(52.129748);
        _longitudes.add(21.063527);
        
        _points.add("F");
        _latitudes.add(52.128900);
        _longitudes.add(21.063548);
        
        _points.add("BALKON");
        _latitudes.add(52.129550);
        _longitudes.add(21.061462);
        */
        
        _currentError = 0.0000001;
        _currentLatitude = 0.0;
        _currentLongitude = 0.0;
        _currentPoint = 0;
        
        _latitudeText = (TextView)findViewById(R.id.latitude);
        _longitudeText = (TextView)findViewById(R.id.longitude);
        _mark = (View)findViewById(R.id.mark);
        _streetText = (TextView)findViewById(R.id.street);
        _toggleErrorButton = (Button)findViewById(R.id.toggleErrorButton);
        _currentErrorText = (TextView)findViewById(R.id.currentError);
        _registerButton = (Button)findViewById(R.id.registerButton);
        
        _toggleErrorButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				onToggleErrorButtonClick();
			}
		});
        
        _currentErrorText.setText(String.valueOf(_currentError));
        
        _registerButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				onRegisterButtonClick();
			}
		});
        
        _manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        _manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }
    
    private void onToggleErrorButtonClick()
    {
    	_currentError *= 10;
    	
    	if (_currentError > 0.001)
    		_currentError = 0.0000001;
    	
    	_currentErrorText.setText(String.valueOf(_currentError));
    }
    
    private void onRegisterButtonClick()
	{
		++_currentPoint;
		
		_latitudes.add(_currentLatitude);
		_longitudes.add(_currentLongitude);
		_points.add(String.valueOf(_currentPoint));
	}
    
	@Override
	public void onLocationChanged(Location location)
	{
		_currentLongitude = location.getLongitude();
		_currentLatitude = location.getLatitude();
		
		boolean detected = false;
		String detectedPoint = "";
		
		_latitudeText.setText(String.valueOf(_currentLatitude));
		_longitudeText.setText(String.valueOf(_currentLongitude));
		
		for (int i = 0; i != _points.size(); ++i)
		{
			if (Math.abs(_latitudes.get(i) - _currentLatitude) <= _currentError && Math.abs(_longitudes.get(i) - _currentLongitude) <= _currentError)
			{				
				detected = true;
				detectedPoint = _points.get(i);
			}
		}
		
		if (detected)
		{
			_streetText.setText(detectedPoint);
			_mark.setBackgroundColor(Color.RED);
		}
		else
		{
			_streetText.setText("");
			_mark.setBackgroundColor(Color.WHITE);
		}
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		// TODO Auto-generated method stub
		
	}
}