package com.fARmework.modules.SpaceGestures.Android._impl;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGestures.Android.*;
import com.google.inject.*;

public class SensorManagerResolver implements ISensorManagerResolver
{
	private final Context _context;

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
