package com.fARmework.modules.SpaceGestures.Java.Gestures;

import com.fARmework.modules.SpaceGestures.Java.*;

public abstract class SpaceGesture
{
	private String _name;
	private Direction[] _pattern;
	
	public SpaceGesture(String name, Direction[] pattern)
	{
		_name = name;
		_pattern = pattern;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public Direction[] getPattern()
	{
		return _pattern;
	}
}
