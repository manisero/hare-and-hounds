package com.fARmework.modules.SpaceGestures.Java;

public class SpaceGesture
{
	private Direction[] _pattern;
	private String _name;
	
	public SpaceGesture(String name, Direction[] pattern)
	{
		_name = name;
		_pattern = pattern;
	}
	
	public Direction[] getPattern()
	{
		return _pattern;
	}
	
	public String getName()
	{
		return _name;
	}
}
