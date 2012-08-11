package com.fARmework.modules.ScreenGestures.Gestures;

import com.fARmework.modules.ScreenGestures.*;

public class PlainGesture implements IGesture<Boolean>
{
	private Boolean[][] _pattern;
	private String _name;
	
	public PlainGesture(Boolean[][] pattern, String name)
	{
		_pattern = pattern;
		_name = name;
	}
	
	@Override
	public Boolean[][] getPattern() 
	{
		return _pattern;
	}

	@Override
	public String getName() 
	{
		return _name;
	}
}
