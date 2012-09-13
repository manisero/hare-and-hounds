package com.fARmework.modules.ScreenGestures.Java.Gestures;

public abstract class ScreenGesture<T> 
{
	protected String Name;
	protected T[][] Pattern;
	
	protected ScreenGesture(String name, T[][] pattern)
	{
		Name = name;
		Pattern = pattern;
	}
	
	public String getName() 
	{
		return Name;
	}
	
	public T[][] getPattern() 
	{
		return Pattern;
	}
	
	public int getPatternSize()
	{
		return Pattern.length;
	}
}
