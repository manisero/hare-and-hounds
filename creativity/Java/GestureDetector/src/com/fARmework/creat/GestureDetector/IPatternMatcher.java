package com.fARmework.creat.GestureDetector;

public interface IPatternMatcher<T> 
{	
	boolean match(T[][] input, T[][] pattern);
}