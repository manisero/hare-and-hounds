package com.fARmework.creat.GestureDetector.Gestures;

import com.fARmework.creat.GestureDetector.IGesture;

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
