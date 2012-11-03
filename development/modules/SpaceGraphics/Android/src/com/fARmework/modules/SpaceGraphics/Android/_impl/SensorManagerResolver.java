package com.fARmework.modules.SpaceGraphics.Android._impl;

import android.content.*;
import android.hardware.*;

import com.fARmework.modules.SpaceGraphics.Android.*;

public class SensorManagerResolver implements ISensorManagerResolver
{
	private Context _context;
	
	@Override
	public void setContext(Context context)
	{
		_context = context;
	}

	@Override
	public SensorManager resolve()
	{
		return (SensorManager)_context.getSystemService(Context.SENSOR_SERVICE);
	}
}
