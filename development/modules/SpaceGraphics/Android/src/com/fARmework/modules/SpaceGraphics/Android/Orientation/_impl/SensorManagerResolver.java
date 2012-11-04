package com.fARmework.modules.SpaceGraphics.Android.Orientation._impl;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGraphics.Android.Orientation.*;
import com.google.inject.*;

public class SensorManagerResolver implements ISensorManagerResolver
{
	private Context _context;
	
	@Inject
	public SensorManagerResolver(Context context)
	{
		_context = context;
	}

	@Override
	public SensorManager resolve()
	{
		return (SensorManager)_context.getSystemService(Context.SENSOR_SERVICE);
	}
}
