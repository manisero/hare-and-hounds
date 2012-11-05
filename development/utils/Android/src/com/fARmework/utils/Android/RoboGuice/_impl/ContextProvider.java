package com.fARmework.utils.Android.RoboGuice._impl;

import com.fARmework.utils.Android.RoboGuice.*;

import android.content.*;

public class ContextProvider implements IContextProvider
{
	private Context _context;
	
	@Override
	public void set(Context context)
	{
		_context = context;
	}
	
	@Override
	public Context get()
	{
		return _context;
	}
}
