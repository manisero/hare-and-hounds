package com.fARmework.modules.ScreenGestures.Java.Matching;

public interface IPatternMatcher<T> 
{	
	boolean match(T[][] input, T[][] pattern);
}