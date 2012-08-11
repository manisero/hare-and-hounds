package com.fARmework.modules.ScreenGestures.Matching;

public interface IPatternMatcher<T> 
{	
	boolean match(T[][] input, T[][] pattern);
}