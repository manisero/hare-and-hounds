package com.fARmework.modules.ScreenGestures.Java.Gestures;

import com.fARmework.modules.ScreenGestures.Java.*;

public class DiffusedGesture implements IGesture<Double> 
{
	private Double[][] _pattern;
	private String _name;
	
	public DiffusedGesture(Double[][] pattern, String name)
	{
		_pattern = pattern;
		_name = name;
	}
	
	@Override
	public Double[][] getPattern() 
	{
		return _pattern;
	}

	@Override
	public String getName() 
	{
		return _name;
	}
}
