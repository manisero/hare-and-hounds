package com.fARmework.modules.SpaceGraphics.Android._impl;

import android.content.*;
import android.view.*;

import com.fARmework.modules.SpaceGraphics.Android.*;

public class DisplayResolver implements IDisplayResolver
{
	private Context _context;
	
	@Override
	public void setContext(Context context)
	{
		_context = context;
	}

	@Override
	public Display resolve()
	{
		return ((WindowManager)_context.getSystemService(Context.SENSOR_SERVICE)).getDefaultDisplay();
	}
}
