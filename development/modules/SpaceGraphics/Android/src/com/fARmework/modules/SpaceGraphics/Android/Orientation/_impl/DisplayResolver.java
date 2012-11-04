package com.fARmework.modules.SpaceGraphics.Android.Orientation._impl;

import android.content.*;
import android.view.*;

import com.fARmework.modules.SpaceGraphics.Android.Orientation.*;
import com.google.inject.*;

public class DisplayResolver implements IDisplayResolver
{
	private Context _context;
	
	@Inject
	public DisplayResolver(Context context)
	{
		_context = context;
	}

	@Override
	public Display resolve()
	{
		return ((WindowManager)_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	}
}
