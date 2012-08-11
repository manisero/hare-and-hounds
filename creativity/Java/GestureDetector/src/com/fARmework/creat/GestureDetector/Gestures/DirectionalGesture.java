package com.fARmework.creat.GestureDetector.Gestures;

import com.fARmework.creat.GestureDetector.*;

public class DirectionalGesture implements IGesture<Integer> 
{
	private Integer[][] _pattern;
	private String _name;
	
	public DirectionalGesture(Integer[][] pattern, String name)
	{
		_pattern = pattern;
		_name = name;
	}
	
	@Override
	public Integer[][] getPattern() 
	{
		return _pattern;
	}

	@Override
	public String getName() 
	{
		return _name;
	}
}
