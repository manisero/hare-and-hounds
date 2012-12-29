package com.fARmework.modules.ScreenGestures.Java.Matching;

public interface IScreenPatternMatcher<T> 
{	
	boolean match(Boolean[][] input, T[][] pattern);
}