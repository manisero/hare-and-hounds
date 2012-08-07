package com.fARmework.creat.GestureDetector.Data;

public class GesturePattern<T> 
{
	private String _name;
	
	private T[][] _pattern;
	
	public GesturePattern(String name, T[][] pattern)
	{
		_name = name;
		_pattern = pattern;
	}
	
	public T[][] getPattern()
	{
		return _pattern;
	}
	
	public String getName()
	{
		return _name;
	}
}
